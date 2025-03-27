
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 菜品
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/caipin")
public class CaipinController {
    private static final Logger logger = LoggerFactory.getLogger(CaipinController.class);

    private static final String TABLE_NAME = "caipin";

    @Autowired
    private CaipinService caipinService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaipinCollectionService caipinCollectionService;//菜品收藏
    @Autowired
    private CaipinCommentbackService caipinCommentbackService;//菜品评价
    @Autowired
    private CaipinOrderService caipinOrderService;//菜品订单
    @Autowired
    private CartService cartService;//购物车
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private FangjianService fangjianService;//客房
    @Autowired
    private FangjianCollectionService fangjianCollectionService;//客房收藏
    @Autowired
    private FangjianCommentbackService fangjianCommentbackService;//客房评价
    @Autowired
    private FangjianOrderService fangjianOrderService;//客房预定
    @Autowired
    private JiankangshangbaoService jiankangshangbaoService;//健康上报
    @Autowired
    private RuzhuService ruzhuService;//入住记录
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("caipinDeleteStart",1);params.put("caipinDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = caipinService.queryPage(params);

        //字典表数据转换
        List<CaipinView> list =(List<CaipinView>)page.getList();
        for(CaipinView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CaipinEntity caipin = caipinService.selectById(id);
        if(caipin !=null){
            //entity转view
            CaipinView view = new CaipinView();
            BeanUtils.copyProperties( caipin , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody CaipinEntity caipin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,caipin:{}",this.getClass().getName(),caipin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<CaipinEntity> queryWrapper = new EntityWrapper<CaipinEntity>()
            .eq("caipin_name", caipin.getCaipinName())
            .eq("caipin_types", caipin.getCaipinTypes())
            .eq("caipin_erji_types", caipin.getCaipinErjiTypes())
            .eq("caipin_kucun_number", caipin.getCaipinKucunNumber())
            .eq("shangxia_types", caipin.getShangxiaTypes())
            .eq("caipin_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CaipinEntity caipinEntity = caipinService.selectOne(queryWrapper);
        if(caipinEntity==null){
            caipin.setCaipinClicknum(1);
            caipin.setShangxiaTypes(1);
            caipin.setCaipinDelete(1);
            caipin.setInsertTime(new Date());
            caipin.setCreateTime(new Date());
            caipinService.insert(caipin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody CaipinEntity caipin, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,caipin:{}",this.getClass().getName(),caipin.toString());
        CaipinEntity oldCaipinEntity = caipinService.selectById(caipin.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(caipin.getCaipinPhoto()) || "null".equals(caipin.getCaipinPhoto())){
                caipin.setCaipinPhoto(null);
        }
        if("".equals(caipin.getCaipinContent()) || "null".equals(caipin.getCaipinContent())){
                caipin.setCaipinContent(null);
        }

            caipinService.updateById(caipin);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<CaipinEntity> oldCaipinList =caipinService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<CaipinEntity> list = new ArrayList<>();
        for(Integer id:ids){
            CaipinEntity caipinEntity = new CaipinEntity();
            caipinEntity.setId(id);
            caipinEntity.setCaipinDelete(2);
            list.add(caipinEntity);
        }
        if(list != null && list.size() >0){
            caipinService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<CaipinEntity> caipinList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            CaipinEntity caipinEntity = new CaipinEntity();
//                            caipinEntity.setCaipinName(data.get(0));                    //菜品名称 要改的
//                            caipinEntity.setCaipinUuidNumber(data.get(0));                    //菜品编号 要改的
//                            caipinEntity.setCaipinPhoto("");//详情和图片
//                            caipinEntity.setCaipinTypes(Integer.valueOf(data.get(0)));   //菜品类型 要改的
//                            caipinEntity.setCaipinErjiTypes(Integer.valueOf(data.get(0)));   //二级类型 要改的
//                            caipinEntity.setCaipinKucunNumber(Integer.valueOf(data.get(0)));   //菜品数量 要改的
//                            caipinEntity.setCaipinOldMoney(data.get(0));                    //菜品原价 要改的
//                            caipinEntity.setCaipinNewMoney(data.get(0));                    //现价/份 要改的
//                            caipinEntity.setCaipinClicknum(Integer.valueOf(data.get(0)));   //菜品热度 要改的
//                            caipinEntity.setCaipinContent("");//详情和图片
//                            caipinEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            caipinEntity.setCaipinDelete(1);//逻辑删除字段
//                            caipinEntity.setInsertTime(date);//时间
//                            caipinEntity.setCreateTime(date);//时间
                            caipinList.add(caipinEntity);


                            //把要查询是否重复的字段放入map中
                                //菜品编号
                                if(seachFields.containsKey("caipinUuidNumber")){
                                    List<String> caipinUuidNumber = seachFields.get("caipinUuidNumber");
                                    caipinUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> caipinUuidNumber = new ArrayList<>();
                                    caipinUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("caipinUuidNumber",caipinUuidNumber);
                                }
                        }

                        //查询是否重复
                         //菜品编号
                        List<CaipinEntity> caipinEntities_caipinUuidNumber = caipinService.selectList(new EntityWrapper<CaipinEntity>().in("caipin_uuid_number", seachFields.get("caipinUuidNumber")).eq("caipin_delete", 1));
                        if(caipinEntities_caipinUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(CaipinEntity s:caipinEntities_caipinUuidNumber){
                                repeatFields.add(s.getCaipinUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [菜品编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        caipinService.insertBatch(caipinList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }



    /**
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<CaipinView> returnCaipinViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("caipinYesnoTypes",2);
        PageUtils pageUtils = caipinOrderService.queryPage(params1);
        List<CaipinOrderView> orderViewsList =(List<CaipinOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(CaipinOrderView orderView:orderViewsList){
            Integer caipinTypes = orderView.getCaipinTypes();
            if(typeMap.containsKey(caipinTypes)){
                typeMap.put(caipinTypes,typeMap.get(caipinTypes)+1);
            }else{
                typeMap.put(caipinTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("caipinTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("caipinYesnoTypes",2);
            PageUtils pageUtils1 = caipinService.queryPage(params2);
            List<CaipinView> caipinViewList =(List<CaipinView>)pageUtils1.getList();
            returnCaipinViewList.addAll(caipinViewList);
            if(returnCaipinViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("caipinYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = caipinService.queryPage(params);
        if(returnCaipinViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnCaipinViewList.size();//要添加的数量
            List<CaipinView> caipinViewList =(List<CaipinView>)page.getList();
            for(CaipinView caipinView:caipinViewList){
                Boolean addFlag = true;
                for(CaipinView returnCaipinView:returnCaipinViewList){
                    if(returnCaipinView.getId().intValue() ==caipinView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnCaipinViewList.add(caipinView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnCaipinViewList = returnCaipinViewList.subList(0, limit);
        }

        for(CaipinView c:returnCaipinViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnCaipinViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = caipinService.queryPage(params);

        //字典表数据转换
        List<CaipinView> list =(List<CaipinView>)page.getList();
        for(CaipinView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CaipinEntity caipin = caipinService.selectById(id);
            if(caipin !=null){

                //点击数量加1
                caipin.setCaipinClicknum(caipin.getCaipinClicknum()+1);
                caipinService.updateById(caipin);

                //entity转view
                CaipinView view = new CaipinView();
                BeanUtils.copyProperties( caipin , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody CaipinEntity caipin, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,caipin:{}",this.getClass().getName(),caipin.toString());
        Wrapper<CaipinEntity> queryWrapper = new EntityWrapper<CaipinEntity>()
            .eq("caipin_name", caipin.getCaipinName())
            .eq("caipin_uuid_number", caipin.getCaipinUuidNumber())
            .eq("caipin_types", caipin.getCaipinTypes())
            .eq("caipin_erji_types", caipin.getCaipinErjiTypes())
            .eq("caipin_kucun_number", caipin.getCaipinKucunNumber())
            .eq("caipin_clicknum", caipin.getCaipinClicknum())
            .eq("shangxia_types", caipin.getShangxiaTypes())
            .eq("caipin_delete", caipin.getCaipinDelete())
//            .notIn("caipin_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CaipinEntity caipinEntity = caipinService.selectOne(queryWrapper);
        if(caipinEntity==null){
            caipin.setCaipinClicknum(1);
            caipin.setCaipinDelete(1);
            caipin.setInsertTime(new Date());
            caipin.setCreateTime(new Date());
        caipinService.insert(caipin);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

