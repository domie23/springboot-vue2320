import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
    // 解决多次点击左侧菜单报错问题
    const VueRouterPush = VueRouter.prototype.push
    VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
    }
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import beifen from '@/views/modules/databaseBackup/beifen'
import huanyuan from '@/views/modules/databaseBackup/huanyuan'

     import users from '@/views/modules/users/list'
    import caipin from '@/views/modules/caipin/list'
    import caipinCollection from '@/views/modules/caipinCollection/list'
    import caipinCommentback from '@/views/modules/caipinCommentback/list'
    import caipinOrder from '@/views/modules/caipinOrder/list'
    import cart from '@/views/modules/cart/list'
    import dictionary from '@/views/modules/dictionary/list'
    import fangjian from '@/views/modules/fangjian/list'
    import fangjianCollection from '@/views/modules/fangjianCollection/list'
    import fangjianCommentback from '@/views/modules/fangjianCommentback/list'
    import fangjianOrder from '@/views/modules/fangjianOrder/list'
    import jiankangshangbao from '@/views/modules/jiankangshangbao/list'
    import ruzhu from '@/views/modules/ruzhu/list'
    import yonghu from '@/views/modules/yonghu/list'
    import config from '@/views/modules/config/list'
    import dictionaryCaipin from '@/views/modules/dictionaryCaipin/list'
    import dictionaryCaipinCollection from '@/views/modules/dictionaryCaipinCollection/list'
    import dictionaryCaipinErji from '@/views/modules/dictionaryCaipinErji/list'
        import dictionaryCaipinErjiAddOrUpdate from '@/views/modules/dictionaryCaipinErji/add-or-update'//二级
    import dictionaryCaipinOrder from '@/views/modules/dictionaryCaipinOrder/list'
    import dictionaryCaipinOrderCanzhuo from '@/views/modules/dictionaryCaipinOrderCanzhuo/list'
    import dictionaryCaipinOrderPayment from '@/views/modules/dictionaryCaipinOrderPayment/list'
    import dictionaryFangjian from '@/views/modules/dictionaryFangjian/list'
    import dictionaryFangjianCollection from '@/views/modules/dictionaryFangjianCollection/list'
    import dictionaryFangjianOrder from '@/views/modules/dictionaryFangjianOrder/list'
    import dictionaryFangjianOrderPayment from '@/views/modules/dictionaryFangjianOrderPayment/list'
    import dictionaryJiankangshangbao from '@/views/modules/dictionaryJiankangshangbao/list'
    import dictionaryJinyong from '@/views/modules/dictionaryJinyong/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryShangxia from '@/views/modules/dictionaryShangxia/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }, {
        path: '/huanyuan',
        name: '数据还原',
        component: huanyuan
    }, {
        path: '/beifen',
        name: '数据备份',
        component: beifen
    }, {
        path: '/users',
        name: '管理信息',
        component: users
    }
    ,{
        path: '/dictionaryCaipin',
        name: '菜品类型',
        component: dictionaryCaipin
    }
    ,{
        path: '/dictionaryCaipinCollection',
        name: '收藏表类型',
        component: dictionaryCaipinCollection
    }
    ,{
        path: '/dictionaryCaipinErji',
        name: '二级类型',
        component: dictionaryCaipinErji
    }
    ,{
        path: '/dictionaryCaipinErjiAddOrUpdate',
        name: '二级类型的新增修改页面',
        component: dictionaryCaipinErjiAddOrUpdate
    }
    ,{
        path: '/dictionaryCaipinOrder',
        name: '订单类型',
        component: dictionaryCaipinOrder
    }
    ,{
        path: '/dictionaryCaipinOrderCanzhuo',
        name: '餐桌',
        component: dictionaryCaipinOrderCanzhuo
    }
    ,{
        path: '/dictionaryCaipinOrderPayment',
        name: '订单支付类型',
        component: dictionaryCaipinOrderPayment
    }
    ,{
        path: '/dictionaryFangjian',
        name: '房型',
        component: dictionaryFangjian
    }
    ,{
        path: '/dictionaryFangjianCollection',
        name: '收藏表类型',
        component: dictionaryFangjianCollection
    }
    ,{
        path: '/dictionaryFangjianOrder',
        name: '订单类型',
        component: dictionaryFangjianOrder
    }
    ,{
        path: '/dictionaryFangjianOrderPayment',
        name: '订单支付类型',
        component: dictionaryFangjianOrderPayment
    }
    ,{
        path: '/dictionaryJiankangshangbao',
        name: '现在状态',
        component: dictionaryJiankangshangbao
    }
    ,{
        path: '/dictionaryJinyong',
        name: '账户状态',
        component: dictionaryJinyong
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShangxia',
        name: '上下架',
        component: dictionaryShangxia
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/caipin',
        name: '菜品',
        component: caipin
      }
    ,{
        path: '/caipinCollection',
        name: '菜品收藏',
        component: caipinCollection
      }
    ,{
        path: '/caipinCommentback',
        name: '菜品评价',
        component: caipinCommentback
      }
    ,{
        path: '/caipinOrder',
        name: '菜品订单',
        component: caipinOrder
      }
    ,{
        path: '/cart',
        name: '购物车',
        component: cart
      }
    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/fangjian',
        name: '客房',
        component: fangjian
      }
    ,{
        path: '/fangjianCollection',
        name: '客房收藏',
        component: fangjianCollection
      }
    ,{
        path: '/fangjianCommentback',
        name: '客房评价',
        component: fangjianCommentback
      }
    ,{
        path: '/fangjianOrder',
        name: '客房预定',
        component: fangjianOrder
      }
    ,{
        path: '/jiankangshangbao',
        name: '健康上报',
        component: jiankangshangbao
      }
    ,{
        path: '/ruzhu',
        name: '入住记录',
        component: ruzhu
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
