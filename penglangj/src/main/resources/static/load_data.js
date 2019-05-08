
window.onload = function () {

    $.ajax({

        type: 'GET',
        url: "user/findAllUser.do",
        contentType: "application/json;cherset=utf-8",
        dataType: "json",
        success: function (data){
    //取出后端传过来的list值
    var chargeRuleLogs = data.userList;
    alert(chargeRuleLogs);
    //对list值进行便利
    $.each(chargeRuleLogs, function (index, n) {

        var rowTr = document.createElement('tr')
        //找到html的tr节点

        rowTr.className = "tr_node"
        var child = "<td>" + chargeRuleLogs[index].id +  "</td>"
            + "<td>" + chargeRuleLogs[index].userName + "</td>"
            + "<td>" + chargeRuleLogs[index].age + "</td>"
            + "<td>"+chargeRuleLogs[index].state + "</td>"
        //将要展示的信息写入页面
        rowTr.innerHTML = child
        //将信息追加
        $(".table_node").append(rowTr)
    });
}
});
}
