
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
 * 健康上报
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiankangshangbao")
public class JiankangshangbaoController {
    private static final Logger logger = LoggerFactory.getLogger(JiankangshangbaoController.class);

    private static final String TABLE_NAME = "jiankangshangbao";

    @Autowired
    private JiankangshangbaoService jiankangshangbaoService;


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
    private FangjianService fangjianService;//客房
    @Autowired
    private FangjianCollectionService fangjianCollectionService;//客房收藏
    @Autowired
    private FangjianCommentbackService fangjianCommentbackService;//客房评价
    @Autowired
    private FangjianOrderService fangjianOrderService;//客房预定
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
        CommonUtil.checkMap(params);
        PageUtils page = jiankangshangbaoService.queryPage(params);

        //字典表数据转换
        List<JiankangshangbaoView> list =(List<JiankangshangbaoView>)page.getList();
        for(JiankangshangbaoView c:list){
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
        JiankangshangbaoEntity jiankangshangbao = jiankangshangbaoService.selectById(id);
        if(jiankangshangbao !=null){
            //entity转view
            JiankangshangbaoView view = new JiankangshangbaoView();
            BeanUtils.copyProperties( jiankangshangbao , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(jiankangshangbao.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
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
    public R save(@RequestBody JiankangshangbaoEntity jiankangshangbao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiankangshangbao:{}",this.getClass().getName(),jiankangshangbao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            jiankangshangbao.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JiankangshangbaoEntity> queryWrapper = new EntityWrapper<JiankangshangbaoEntity>()
            .eq("yonghu_id", jiankangshangbao.getYonghuId())
            .eq("jiankangshangbao_types", jiankangshangbao.getJiankangshangbaoTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiankangshangbaoEntity jiankangshangbaoEntity = jiankangshangbaoService.selectOne(queryWrapper);
        if(jiankangshangbaoEntity==null){
            jiankangshangbao.setInsertTime(new Date());
            jiankangshangbao.setCreateTime(new Date());
            jiankangshangbaoService.insert(jiankangshangbao);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiankangshangbaoEntity jiankangshangbao, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jiankangshangbao:{}",this.getClass().getName(),jiankangshangbao.toString());
        JiankangshangbaoEntity oldJiankangshangbaoEntity = jiankangshangbaoService.selectById(jiankangshangbao.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            jiankangshangbao.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(jiankangshangbao.getJiankangshangbaoTiwenPhoto()) || "null".equals(jiankangshangbao.getJiankangshangbaoTiwenPhoto())){
                jiankangshangbao.setJiankangshangbaoTiwenPhoto(null);
        }
        if("".equals(jiankangshangbao.getJiankangshangbaoContent()) || "null".equals(jiankangshangbao.getJiankangshangbaoContent())){
                jiankangshangbao.setJiankangshangbaoContent(null);
        }

            jiankangshangbaoService.updateById(jiankangshangbao);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JiankangshangbaoEntity> oldJiankangshangbaoList =jiankangshangbaoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        jiankangshangbaoService.deleteBatchIds(Arrays.asList(ids));

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
            List<JiankangshangbaoEntity> jiankangshangbaoList = new ArrayList<>();//上传的东西
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
                            JiankangshangbaoEntity jiankangshangbaoEntity = new JiankangshangbaoEntity();
//                            jiankangshangbaoEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            jiankangshangbaoEntity.setJiankangshangbaoUuidNumber(data.get(0));                    //健康上报编号 要改的
//                            jiankangshangbaoEntity.setJiankangshangbaoTypes(Integer.valueOf(data.get(0)));   //现在状态 要改的
//                            jiankangshangbaoEntity.setJiankangshangbaoTiwenPhoto("");//详情和图片
//                            jiankangshangbaoEntity.setJiankangshangbaoTiwen(data.get(0));                    //体温 要改的
//                            jiankangshangbaoEntity.setJiankangshangbaoContent("");//详情和图片
//                            jiankangshangbaoEntity.setInsertTime(date);//时间
//                            jiankangshangbaoEntity.setCreateTime(date);//时间
                            jiankangshangbaoList.add(jiankangshangbaoEntity);


                            //把要查询是否重复的字段放入map中
                                //健康上报编号
                                if(seachFields.containsKey("jiankangshangbaoUuidNumber")){
                                    List<String> jiankangshangbaoUuidNumber = seachFields.get("jiankangshangbaoUuidNumber");
                                    jiankangshangbaoUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jiankangshangbaoUuidNumber = new ArrayList<>();
                                    jiankangshangbaoUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("jiankangshangbaoUuidNumber",jiankangshangbaoUuidNumber);
                                }
                        }

                        //查询是否重复
                         //健康上报编号
                        List<JiankangshangbaoEntity> jiankangshangbaoEntities_jiankangshangbaoUuidNumber = jiankangshangbaoService.selectList(new EntityWrapper<JiankangshangbaoEntity>().in("jiankangshangbao_uuid_number", seachFields.get("jiankangshangbaoUuidNumber")));
                        if(jiankangshangbaoEntities_jiankangshangbaoUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiankangshangbaoEntity s:jiankangshangbaoEntities_jiankangshangbaoUuidNumber){
                                repeatFields.add(s.getJiankangshangbaoUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [健康上报编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jiankangshangbaoService.insertBatch(jiankangshangbaoList);
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
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = jiankangshangbaoService.queryPage(params);

        //字典表数据转换
        List<JiankangshangbaoView> list =(List<JiankangshangbaoView>)page.getList();
        for(JiankangshangbaoView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiankangshangbaoEntity jiankangshangbao = jiankangshangbaoService.selectById(id);
            if(jiankangshangbao !=null){


                //entity转view
                JiankangshangbaoView view = new JiankangshangbaoView();
                BeanUtils.copyProperties( jiankangshangbao , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(jiankangshangbao.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody JiankangshangbaoEntity jiankangshangbao, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jiankangshangbao:{}",this.getClass().getName(),jiankangshangbao.toString());
        Wrapper<JiankangshangbaoEntity> queryWrapper = new EntityWrapper<JiankangshangbaoEntity>()
            .eq("yonghu_id", jiankangshangbao.getYonghuId())
            .eq("jiankangshangbao_uuid_number", jiankangshangbao.getJiankangshangbaoUuidNumber())
            .eq("jiankangshangbao_types", jiankangshangbao.getJiankangshangbaoTypes())
//            .notIn("jiankangshangbao_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiankangshangbaoEntity jiankangshangbaoEntity = jiankangshangbaoService.selectOne(queryWrapper);
        if(jiankangshangbaoEntity==null){
            jiankangshangbao.setInsertTime(new Date());
            jiankangshangbao.setCreateTime(new Date());
        jiankangshangbaoService.insert(jiankangshangbao);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

