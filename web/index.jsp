<%-- 
    Document   : main
    Created on : 21.11.2016, 9:47:37
    Author     : user
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${manager.getString("company-name")}</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/carousel.css" rel="stylesheet">
        <link rel="stylesheet" href="css/demo.css">
        <link rel="stylesheet" href="css/footer-distributed-with-address-and-phones.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        
    </head>

    <body>
    <div class="navbar-wrapper">
        <div class="container">
            <div class="navbar navbar-inverse navbar-static-top" role="navigation">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
                        <a class="navbar-brand" href="controller?action=gethomepageabonent">${manager.getString("company-name")}</a>
                    </div>
                    <div id="sidebar-collapse" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="controller?action=gethomepageabonent">${manager.getString("nav-link1")}</a></li>
                            <li><a href="controller?action=getservicesclient">${manager.getString("nav-link2")}</a></li>
                            <li><a href="controller?action=getabout">${manager.getString("nav-link3")}</a></li>
                            <li><a href="controller?action=getcontacts">${manager.getString("nav-link4")}</a></li>
                        </ul>
                        <%@include file="partial/loginbox.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
                    
    <%@include file="partial/slyder.jsp" %>

    <div class="container marketing">
        <div class="row">
            <div class="col-lg-4">
                <img style="width: 180px; height: 180px;" class="img-circle" data-src="" alt="140x140" src="image/services.jpg" style="width: 140px; height: 140px;">
                <h2>${manager.getString("block1-head-text")}</h2>
                <p>${manager.getString("block1-content-text")}</p>
                <p><a class="btn btn-default" href="controller?action=getservicesclient" role="button">${manager.getString("block-button")} »</a></p>
            </div>
            <div class="col-lg-4">
                <img style="width: 180px; height: 180px;" class="img-circle" data-src="" alt="140x140" src="image/technolog.gif" style="width: 140px; height: 140px;">
                <h2>${manager.getString("block2-head-text")}</h2>
                <p>${manager.getString("block2-content-text")}</p>
                <p><a class="btn btn-default" href="#" role="button">${manager.getString("block-button")} »</a></p>
            </div>
            <div class="col-lg-4">
                <img style="width: 180px; height: 180px;" class="img-circle" data-src="" alt="140x140" src="image/consult.png" style="width: 140px; height: 140px;">
                <h2>${manager.getString("block3-head-text")}</h2>
                <p>${manager.getString("block3-content-text")}</p>
                <p><a class="btn btn-default" href="controller?action=getcontacts" role="button">${manager.getString("block-button")} »</a></p>
            </div>
        </div>
    </div>
    
       <div id="myModalBox" class="modal fade">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header" style="background-color: #30A5FF; color:white;">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 style="color: white;" class="modal-title">${manager.getString("modal-changepassword-title")}</h4>
              </div>
              <div class="modal-body">
                <form method="POST" action="controller">
                  <input type="hidden" name="action" value="changepassword" />
                  <input type="hidden" name="id" value="${abonent.getId()}" />
                  <fieldset>
                  <div id="newpass-block" class="form-group">
                    <label for="newpassword">${manager.getString("modal-changepassword-label1")}: </label>
                    <input type="password" required class="form-control" id="newpassword" required name="newpassword" placeholder="${manager.getString("modal-changepassword-input1")}">
                    <span id="newpass-span" class="glyphicon form-control-feedback"></span>
                  </div>
                  <div id="newpass1-block" class="form-group">
                    <label for="newpassword1">${manager.getString("modal-changepassword-label2")}: </label>
                    <input type="password" class="form-control" required id="newpassword1" name="newpassword1" placeholder="${manager.getString("modal-changepassword-input2")}">
                    <span id="newpass1-span" class="glyphicon form-control-feedback"></span>
                  </div>
                  <div class="form-group">
                      <div class="pull-right">
                          <button type="button" class="btn btn-default" data-dismiss="modal">${manager.getString("modal-changepassword-button1")}</button>
                        <button id="btn-conf" type="submit"   class="btn btn-primary">${manager.getString("modal-changepassword-button2")}</button>
                      </div>
                  </div>
                  </fieldset>
                </form>
              </div>
            </div>
          </div>
        </div>
            
            
    <%@include file="partial/footer.jsp" %>

    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/lumino.glyphs.js"></script>
          <script>
              $(window).load(function() {
                  $.ajax ({ 
    type: "GET",
    url: 'controller?action=gethomepageabonent'
});
              })    ;
              $(document).ready(function(){
            //при нажатию на любую кнопку, имеющую класс .btn
            $(".link-change").click(function() {
            //открыть модальное окно с id="myModal"
            $("#myModalBox").modal('show');
            });
              $('#btn-conf').attr('disabled', true);
   $('#newpassword').on('keyup',function() {
       if($(this).val().length > 6) {
            $('#newpass-block').addClass('has-success has-feedback');
            $('#newpass-span').addClass('glyphicon-ok');
            $('#newpass-block').removeClass('has-error');
            $('#newpass-span').removeClass('glyphicon-warning-sign');
       }else{
            $('#newpass-block').addClass('has-error has-feedback');
            $('#newpass-span').addClass('glyphicon-warning-sign');
            $('#newpass-block').removeClass('has-success');
            $('#newpass-span').removeClass('glyphicon-ok');
       }
   });
    
     $('#newpassword1').on('keyup',function() {
        var value = $('#newpassword').val();
        if($(this).val() == value && $(this).val() != '' && $(this).val().length > 6) {
            $('#btn-conf').attr('disabled' , false);
            $('#newpass1-block').addClass('has-success has-feedback');
            $('#newpass1-span').addClass('glyphicon-ok');
            $('#newpass-block').addClass('has-success has-feedback');
            $('#newpass-span').addClass('glyphicon-ok');
            $('#newpass1-block').removeClass('has-error');
            $('#newpass1-span').removeClass('glyphicon-warning-sign');
            $('#newpass-block').removeClass('has-error');
            $('#newpass-span').removeClass('glyphicon-warning-sign');
        }else{
            $('#btn-conf').attr('disabled' , true);
            $('#newpass1-block').addClass('has-error has-feedback');
            $('#newpass1-span').addClass('glyphicon-warning-sign');
            $('#newpass-block').addClass('has-error has-feedback');
            $('#newpass-span').addClass('glyphicon-warning-sign');
            $('#newpass1-block').removeClass('has-success');
            $('#newpass1-span').removeClass('glyphicon-ok');
            $('#newpass-block').removeClass('has-success');
            $('#newpass-span').removeClass('glyphicon-ok');
        }
    });
            
            });


        $(window).on('resize', function () {
          if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
        })
        $(window).on('resize', function () {
          if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
        })
        </script>
</body>
</html>