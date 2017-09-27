<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%request.setAttribute("contextPath", request.getContextPath()); %>
    <script src="${contextPath}/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
    <title>上传基金净值</title>
    <script type="text/javascript">
        function test(){
            var form = new FormData(document.getElementById("uploadFile"));
            $.ajax({
                       url:"http://www.fofms.net:8888/fps/common/upload.fps",
                       type:"post",
                       data:form,
                       processData:false,
                       contentType:false,
                       success:function(data){
                           var file_id=data.body.file_id;
                           var fund_id='${query.id}';
                           $.ajax({
                                      url:"http://www.fofms.net:8888/fps/fund/add_fund_net_value.fps",
                                      type:"post",
                                      contentType:"application/json",  //  ---->  问题就在这里了
                                      dataType:'json',
                                      data:JSON.stringify({ 'file_id': file_id, 'fund_id': fund_id }),
                                      success:function(data){
                                          alert(data.result_code)
                                          if(data.result_code=='8200'){
                                              alert("添加成功！")
                                          }else {
                                              alert(data.result_msg);
                                          }
                                          console.log("over..");
                                      },
                                      error:function(e){
                                          alert(data.result_code)
                                          alert("添加失败！");
                                      }
                                  });

                           console.log("over..");
                       },
                       error:function(e){
                           alert("添加失败！！！");
                       }
                   });
        }
    </script>
</head>
<body>
<form id="uploadFile" name="uploadFile" action="" method="post" enctype="multipart/form-data" >
    <div >
        <table style="align-content: center">
            <tr>
                <td>
                    <input type="file" name="file"  />
                </td>
                <td>
                    <input type="button" value="上传" onclick="test()"/>
                </td>
            </tr>
        </table>

    </div>
</form>
</body>
</html>