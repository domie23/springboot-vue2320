const base = {
    get() {
        return {
            url : "http://localhost:8080/gelijiudian/",
            name: "gelijiudian",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/gelijiudian/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "疫情隔离酒店管理系统"
        } 
    }
}
export default base
