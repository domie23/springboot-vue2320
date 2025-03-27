
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
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
 * 客房预定
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/fangjianOrder")
public class FangjianOrderController {
    private static final Logger logger = LoggerFactory.getLogger(FangjianOrderController.class);

    private static final String TABLE_NAME = "fangjianOrder";

    @Autowired
    private FangjianOrderService fangjianOrderService;


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
        CommonUtil.checkMap(params);
        PageUtils page = fangjianOrderService.queryPage(params);

        //字典表数据转换
        List<FangjianOrderView> list =(List<FangjianOrderView>)page.getList();
        for(FangjianOrderView c:list){
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
        FangjianOrderEntity fangjianOrder = fangjianOrderService.selectById(id);
        if(fangjianOrder !=null){
            //entity转view
            FangjianOrderView view = new FangjianOrderView();
            BeanUtils.copyProperties( fangjianOrder , view );//把实体数据重构到view中
            //级联表 客房
            //级联表
            FangjianEntity fangjian = fangjianService.selectById(fangjianOrder.getFangjianId());
            if(fangjian != null){
            BeanUtils.copyProperties( fangjian , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setFangjianId(fangjian.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(fangjianOrder.getYonghuId());
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
    public R save(@RequestBody FangjianOrderEntity fangjianOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fangjianOrder:{}",this.getClass().getName(),fangjianOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            fangjianOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        fangjianOrder.setCreateTime(new Date());
        fangjianOrder.setInsertTime(new Date());
        fangjianOrderService.insert(fangjianOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FangjianOrderEntity fangjianOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,fangjianOrder:{}",this.getClass().getName(),fangjianOrder.toString());
        FangjianOrderEntity oldFangjianOrderEntity = fangjianOrderService.selectById(fangjianOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            fangjianOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            fangjianOrderService.updateById(fangjianOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<FangjianOrderEntity> oldFangjianOrderList =fangjianOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        fangjianOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<FangjianOrderEntity> fangjianOrderList = new ArrayList<>();//上传的东西
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
                            FangjianOrderEntity fangjianOrderEntity = new FangjianOrderEntity();
//                            fangjianOrderEntity.setFangjianOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            fangjianOrderEntity.setFangjianId(Integer.valueOf(data.get(0)));   //客房 要改的
//                            fangjianOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            fangjianOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //预定天数 要改的
//                            fangjianOrderEntity.setFangjianOrderTime(sdf.parse(data.get(0)));          //入住时间 要改的
//                            fangjianOrderEntity.setFangjianOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            fangjianOrderEntity.setFangjianOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            fangjianOrderEntity.setFangjianOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            fangjianOrderEntity.setInsertTime(date);//时间
//                            fangjianOrderEntity.setCreateTime(date);//时间
                            fangjianOrderList.add(fangjianOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("fangjianOrderUuidNumber")){
                                    List<String> fangjianOrderUuidNumber = seachFields.get("fangjianOrderUuidNumber");
                                    fangjianOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> fangjianOrderUuidNumber = new ArrayList<>();
                                    fangjianOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("fangjianOrderUuidNumber",fangjianOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<FangjianOrderEntity> fangjianOrderEntities_fangjianOrderUuidNumber = fangjianOrderService.selectList(new EntityWrapper<FangjianOrderEntity>().in("fangjian_order_uuid_number", seachFields.get("fangjianOrderUuidNumber")));
                        if(fangjianOrderEntities_fangjianOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FangjianOrderEntity s:fangjianOrderEntities_fangjianOrderUuidNumber){
                                repeatFields.add(s.getFangjianOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        fangjianOrderService.insertBatch(fangjianOrderList);
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
        PageUtils page = fangjianOrderService.queryPage(params);

        //字典表数据转换
        List<FangjianOrderView> list =(List<FangjianOrderView>)page.getList();
        for(FangjianOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FangjianOrderEntity fangjianOrder = fangjianOrderService.selectById(id);
            if(fangjianOrder !=null){


                //entity转view
                FangjianOrderView view = new FangjianOrderView();
                BeanUtils.copyProperties( fangjianOrder , view );//把实体数据重构到view中

                //级联表
                    FangjianEntity fangjian = fangjianService.selectById(fangjianOrder.getFangjianId());
                if(fangjian != null){
                    BeanUtils.copyProperties( fangjian , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setFangjianId(fangjian.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(fangjianOrder.getYonghuId());
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
    public R add(@RequestBody FangjianOrderEntity fangjianOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,fangjianOrder:{}",this.getClass().getName(),fangjianOrder.toString());
            FangjianEntity fangjianEntity = fangjianService.selectById(fangjianOrder.getFangjianId());
            if(fangjianEntity == null){
                return R.error(511,"查不到该客房");
            }
            // Double fangjianNewMoney = fangjianEntity.getFangjianNewMoney();

            if(false){
            }
            else if(fangjianEntity.getFangjianNewMoney() == null){
                return R.error(511,"现价/天不能为空");
            }
            else if((fangjianEntity.getFangjianKucunNumber() -fangjianOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - fangjianEntity.getFangjianNewMoney()*fangjianOrder.getBuyNumber();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            fangjianOrder.setFangjianOrderTypes(101); //设置订单状态为已预定客房
            fangjianOrder.setFangjianOrderTruePrice(fangjianEntity.getFangjianNewMoney()*fangjianOrder.getBuyNumber()); //设置实付价格
            fangjianOrder.setYonghuId(userId); //设置订单支付人id
            fangjianOrder.setFangjianOrderUuidNumber(String.valueOf(new Date().getTime()));
            fangjianOrder.setFangjianOrderPaymentTypes(1);
            fangjianOrder.setInsertTime(new Date());
            fangjianOrder.setCreateTime(new Date());
                fangjianEntity.setFangjianKucunNumber( fangjianEntity.getFangjianKucunNumber() -fangjianOrder.getBuyNumber());
                fangjianService.updateById(fangjianEntity);
                fangjianOrderService.insert(fangjianOrder);//新增订单
            //更新第一注册表
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);


            return R.ok();
    }
    /**
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request) throws ParseException {
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String fangjianOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");

            Integer fangjianOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("fangjianOrderPaymentTypes")));//支付类型
        String fangjianOrderTime = String.valueOf(params.get("fangjianOrderTime"));//预约时间


        String data = String.valueOf(params.get("fangjians"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> fangjians = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<FangjianOrderEntity> fangjianOrderList = new ArrayList<>();
        //商品表
        List<FangjianEntity> fangjianList = new ArrayList<>();
        //购物车ids
        List<Integer> cartIds = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);

        //循环取出需要的数据
        for (Map<String, Object> map : fangjians) {
           //取值
            Integer fangjianId = Integer.valueOf(String.valueOf(map.get("fangjianId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            FangjianEntity fangjianEntity = fangjianService.selectById(fangjianId);//购买的商品
            String id = String.valueOf(map.get("id"));
            if(StringUtil.isNotEmpty(id))
                cartIds.add(Integer.valueOf(id));

            //判断商品的库存是否足够
            if(fangjianEntity.getFangjianKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(fangjianEntity.getFangjianName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                fangjianEntity.setFangjianKucunNumber(fangjianEntity.getFangjianKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            FangjianOrderEntity fangjianOrderEntity = new FangjianOrderEntity<>();

            //赋值订单信息
            fangjianOrderEntity.setFangjianOrderUuidNumber(fangjianOrderUuidNumber);//订单编号
            fangjianOrderEntity.setFangjianId(fangjianId);//客房
                        fangjianOrderEntity.setYonghuId(userId);//用户
            fangjianOrderEntity.setBuyNumber(buyNumber);//预定天数 ？？？？？？

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fangjianOrderEntity.setFangjianOrderTime(sdf.parse(fangjianOrderTime));//入住时间 ？？？？？？
            fangjianOrderEntity.setFangjianOrderTypes(101);//订单类型
            fangjianOrderEntity.setFangjianOrderPaymentTypes(fangjianOrderPaymentTypes);//支付类型
            fangjianOrderEntity.setInsertTime(new Date());//订单创建时间
            fangjianOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
            if(fangjianOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(fangjianEntity.getFangjianNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(yonghuEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    //计算所获得积分
                    Double buyJifen =0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money); //设置金额


                    fangjianOrderEntity.setFangjianOrderTruePrice(money);

                }
            }
            fangjianOrderList.add(fangjianOrderEntity);
            fangjianList.add(fangjianEntity);

        }
        fangjianOrderService.insertBatch(fangjianOrderList);
        fangjianService.updateBatchById(fangjianList);
        yonghuService.updateById(yonghuEntity);
        if(cartIds != null && cartIds.size()>0)
            cartService.deleteBatchIds(cartIds);

        return R.ok();
    }


    /**
    * 取消预定
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            FangjianOrderEntity fangjianOrder = fangjianOrderService.selectById(id);//当前表service
            Integer buyNumber = fangjianOrder.getBuyNumber();
            Integer fangjianOrderPaymentTypes = fangjianOrder.getFangjianOrderPaymentTypes();
            Integer fangjianId = fangjianOrder.getFangjianId();
            if(fangjianId == null)
                return R.error(511,"查不到该客房");
            FangjianEntity fangjianEntity = fangjianService.selectById(fangjianId);
            if(fangjianEntity == null)
                return R.error(511,"查不到该客房");
            Double fangjianNewMoney = fangjianEntity.getFangjianNewMoney();
            if(fangjianNewMoney == null)
                return R.error(511,"客房价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
            return R.error(511,"用户金额不能为空");
            Double zhekou = 1.0;

            //判断是什么支付方式 1代表余额 2代表积分
            if(fangjianOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = fangjianEntity.getFangjianNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额


            }

            fangjianEntity.setFangjianKucunNumber(fangjianEntity.getFangjianKucunNumber() + buyNumber);

            fangjianOrder.setFangjianOrderTypes(102);//设置订单状态为已取消预定
            fangjianOrderService.updateAllColumnById(fangjianOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            fangjianService.updateById(fangjianEntity);//更新订单中客房的信息

            return R.ok();
    }

    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer fangjianCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            FangjianOrderEntity fangjianOrder = fangjianOrderService.selectById(id);
        if(fangjianOrder == null)
            return R.error(511,"查不到该订单");
        Integer fangjianId = fangjianOrder.getFangjianId();
        if(fangjianId == null)
            return R.error(511,"查不到该客房");

        FangjianCommentbackEntity fangjianCommentbackEntity = new FangjianCommentbackEntity();
            fangjianCommentbackEntity.setId(id);
            fangjianCommentbackEntity.setFangjianId(fangjianId);
            fangjianCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            fangjianCommentbackEntity.setFangjianCommentbackText(commentbackText);
            fangjianCommentbackEntity.setInsertTime(new Date());
            fangjianCommentbackEntity.setReplyText(null);
            fangjianCommentbackEntity.setUpdateTime(null);
            fangjianCommentbackEntity.setCreateTime(new Date());
            fangjianCommentbackService.insert(fangjianCommentbackEntity);

            fangjianOrder.setFangjianOrderTypes(105);//设置订单状态为已评价
            fangjianOrderService.updateById(fangjianOrder);//根据id更新
            return R.ok();
    }

    /**
     * 使用客房
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        FangjianOrderEntity  fangjianOrderEntity = fangjianOrderService.selectById(id);
        fangjianOrderEntity.setFangjianOrderTypes(103);//设置订单状态为已使用客房
        fangjianOrderService.updateById( fangjianOrderEntity);

        return R.ok();
    }


}

