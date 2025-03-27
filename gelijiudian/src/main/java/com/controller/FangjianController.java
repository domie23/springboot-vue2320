
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
 * 客房
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/fangjian")
public class FangjianController {
    private static final Logger logger = LoggerFactory.getLogger(FangjianController.class);

    private static final String TABLE_NAME = "fangjian";

    @Autowired
    private FangjianService fangjianService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaipinService caipinService;//菜品
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
        params.put("fangjianDeleteStart",1);params.put("fangjianDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = fangjianService.queryPage(params);

        //字典表数据转换
        List<FangjianView> list =(List<FangjianView>)page.getList();
        for(FangjianView c:list){
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
        FangjianEntity fangjian = fangjianService.selectById(id);
        if(fangjian !=null){
            //entity转view
            FangjianView view = new FangjianView();
            BeanUtils.copyProperties( fangjian , view );//把实体数据重构到view中
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
    public R save(@RequestBody FangjianEntity fangjian, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fangjian:{}",this.getClass().getName(),fangjian.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<FangjianEntity> queryWrapper = new EntityWrapper<FangjianEntity>()
            .eq("fangjian_name", fangjian.getFangjianName())
            .eq("fangjian_types", fangjian.getFangjianTypes())
            .eq("fangjian_kucun_number", fangjian.getFangjianKucunNumber())
            .eq("shangxia_types", fangjian.getShangxiaTypes())
            .eq("fangjian_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangjianEntity fangjianEntity = fangjianService.selectOne(queryWrapper);
        if(fangjianEntity==null){
            fangjian.setFangjianClicknum(1);
            fangjian.setShangxiaTypes(1);
            fangjian.setFangjianDelete(1);
            fangjian.setInsertTime(new Date());
            fangjian.setCreateTime(new Date());
            fangjianService.insert(fangjian);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FangjianEntity fangjian, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,fangjian:{}",this.getClass().getName(),fangjian.toString());
        FangjianEntity oldFangjianEntity = fangjianService.selectById(fangjian.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(fangjian.getFangjianPhoto()) || "null".equals(fangjian.getFangjianPhoto())){
                fangjian.setFangjianPhoto(null);
        }
        if("".equals(fangjian.getFangjianContent()) || "null".equals(fangjian.getFangjianContent())){
                fangjian.setFangjianContent(null);
        }

            fangjianService.updateById(fangjian);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<FangjianEntity> oldFangjianList =fangjianService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<FangjianEntity> list = new ArrayList<>();
        for(Integer id:ids){
            FangjianEntity fangjianEntity = new FangjianEntity();
            fangjianEntity.setId(id);
            fangjianEntity.setFangjianDelete(2);
            list.add(fangjianEntity);
        }
        if(list != null && list.size() >0){
            fangjianService.updateBatchById(list);
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
            List<FangjianEntity> fangjianList = new ArrayList<>();//上传的东西
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
                            FangjianEntity fangjianEntity = new FangjianEntity();
//                            fangjianEntity.setFangjianName(data.get(0));                    //客房名称 要改的
//                            fangjianEntity.setFangjianUuidNumber(data.get(0));                    //客房编号 要改的
//                            fangjianEntity.setFangjianPhoto("");//详情和图片
//                            fangjianEntity.setFangjianTypes(Integer.valueOf(data.get(0)));   //房型 要改的
//                            fangjianEntity.setFangjianKucunNumber(Integer.valueOf(data.get(0)));   //剩余房数 要改的
//                            fangjianEntity.setFangjianOldMoney(data.get(0));                    //客房原价 要改的
//                            fangjianEntity.setFangjianNewMoney(data.get(0));                    //现价/天 要改的
//                            fangjianEntity.setFangjianClicknum(Integer.valueOf(data.get(0)));   //客房热度 要改的
//                            fangjianEntity.setFangjianContent("");//详情和图片
//                            fangjianEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            fangjianEntity.setFangjianDelete(1);//逻辑删除字段
//                            fangjianEntity.setInsertTime(date);//时间
//                            fangjianEntity.setCreateTime(date);//时间
                            fangjianList.add(fangjianEntity);


                            //把要查询是否重复的字段放入map中
                                //客房编号
                                if(seachFields.containsKey("fangjianUuidNumber")){
                                    List<String> fangjianUuidNumber = seachFields.get("fangjianUuidNumber");
                                    fangjianUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> fangjianUuidNumber = new ArrayList<>();
                                    fangjianUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("fangjianUuidNumber",fangjianUuidNumber);
                                }
                        }

                        //查询是否重复
                         //客房编号
                        List<FangjianEntity> fangjianEntities_fangjianUuidNumber = fangjianService.selectList(new EntityWrapper<FangjianEntity>().in("fangjian_uuid_number", seachFields.get("fangjianUuidNumber")).eq("fangjian_delete", 1));
                        if(fangjianEntities_fangjianUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FangjianEntity s:fangjianEntities_fangjianUuidNumber){
                                repeatFields.add(s.getFangjianUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [客房编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        fangjianService.insertBatch(fangjianList);
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
        List<FangjianView> returnFangjianViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("fangjianYesnoTypes",2);
        PageUtils pageUtils = fangjianOrderService.queryPage(params1);
        List<FangjianOrderView> orderViewsList =(List<FangjianOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(FangjianOrderView orderView:orderViewsList){
            Integer fangjianTypes = orderView.getFangjianTypes();
            if(typeMap.containsKey(fangjianTypes)){
                typeMap.put(fangjianTypes,typeMap.get(fangjianTypes)+1);
            }else{
                typeMap.put(fangjianTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("fangjianTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("fangjianYesnoTypes",2);
            PageUtils pageUtils1 = fangjianService.queryPage(params2);
            List<FangjianView> fangjianViewList =(List<FangjianView>)pageUtils1.getList();
            returnFangjianViewList.addAll(fangjianViewList);
            if(returnFangjianViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("fangjianYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = fangjianService.queryPage(params);
        if(returnFangjianViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnFangjianViewList.size();//要添加的数量
            List<FangjianView> fangjianViewList =(List<FangjianView>)page.getList();
            for(FangjianView fangjianView:fangjianViewList){
                Boolean addFlag = true;
                for(FangjianView returnFangjianView:returnFangjianViewList){
                    if(returnFangjianView.getId().intValue() ==fangjianView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnFangjianViewList.add(fangjianView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnFangjianViewList = returnFangjianViewList.subList(0, limit);
        }

        for(FangjianView c:returnFangjianViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnFangjianViewList);
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
        PageUtils page = fangjianService.queryPage(params);

        //字典表数据转换
        List<FangjianView> list =(List<FangjianView>)page.getList();
        for(FangjianView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FangjianEntity fangjian = fangjianService.selectById(id);
            if(fangjian !=null){

                //点击数量加1
                fangjian.setFangjianClicknum(fangjian.getFangjianClicknum()+1);
                fangjianService.updateById(fangjian);

                //entity转view
                FangjianView view = new FangjianView();
                BeanUtils.copyProperties( fangjian , view );//把实体数据重构到view中

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
    public R add(@RequestBody FangjianEntity fangjian, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,fangjian:{}",this.getClass().getName(),fangjian.toString());
        Wrapper<FangjianEntity> queryWrapper = new EntityWrapper<FangjianEntity>()
            .eq("fangjian_name", fangjian.getFangjianName())
            .eq("fangjian_uuid_number", fangjian.getFangjianUuidNumber())
            .eq("fangjian_types", fangjian.getFangjianTypes())
            .eq("fangjian_kucun_number", fangjian.getFangjianKucunNumber())
            .eq("fangjian_clicknum", fangjian.getFangjianClicknum())
            .eq("shangxia_types", fangjian.getShangxiaTypes())
            .eq("fangjian_delete", fangjian.getFangjianDelete())
//            .notIn("fangjian_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangjianEntity fangjianEntity = fangjianService.selectOne(queryWrapper);
        if(fangjianEntity==null){
            fangjian.setFangjianClicknum(1);
            fangjian.setFangjianDelete(1);
            fangjian.setInsertTime(new Date());
            fangjian.setCreateTime(new Date());
        fangjianService.insert(fangjian);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

