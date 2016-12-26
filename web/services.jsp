<%-- 
    Document   : tables
    Created on : 22.11.2016, 10:36:11
    Author     : user
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${manager.getString("services-page-title")}</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-table.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">

</head>

<body>
    <%@include file="partial/headadmin.jsp" %>	
    <div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
        <ul class="nav menu">
            <li><a href="controller?action=getmain"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg> ${manager.getString("main-page-nav-link1")}</a></li>
            <li><a href="controller?action=getabonents"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg> ${manager.getString("main-page-nav-link2")}</a></li>
            <li class="active"><a href="controller?action=getservices"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg> ${manager.getString("main-page-nav-link3")}</a></li>
            <li style="border-top: #0088cc solid 1px"><a href="controller?action=getadmins"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> ${manager.getString("main-page-nav-link4")}</a></li>
        </ul>
    </div>
		
    <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
        <div class="row">
            <ol class="breadcrumb">
                <li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
                <li class="active">${manager.getString("services-page-head1")}</li>
            </ol>
        </div>
        <div style="margin-top:15px;" class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">${manager.getString("services-page-head2")}</div>
                    <div class="panel-body">
                        <button data-target="#myModalBox" style="float:left; margin-top: 10px;" type="button" class="btn btn-success" data-toggle="modal">${manager.getString("services-page-button")}</button>
                        <table class="table table-striped" data-toggle="table"  data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true"  data-pagination="true" data-sort-name="name" data-sort-order="desc">
                            <thead>
                                <tr>
                                    <th data-field="id" data-sortable="true">№</th>
                                    <th data-field="name" data-sortable="true">${manager.getString("services-page-table-head1")}</th>
                                    <th data-field="description"  data-sortable="true">${manager.getString("services-page-table-head2")}</th>
                                    <th data-field="price" data-sortable="true">${manager.getString("services-page-table-head3")}</th>
                                    <th>${manager.getString("services-page-table-head4")}</th>
                                </tr>
                            </thead>
                            <tbody>                        
                                <c:forEach var="service" items="${services}">
                                    <tr>
                                        <td><c:out value="${service.getId()}"/></td>
                                        <td><c:out value="${service.getName()}"/></td>
                                        <td><c:out value="${service.getDescription()}"/></td>
                                        <td><c:out value="${service.getPrice()}"/></td>
                                        <td>
                                            <a href="controller?action=detailservice&id=${service.getId()}"><span title="${manager.getString("services-page-table-span1")}" style="font-size:19px;padding-left: 7px;" class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                                            <a href="controller?action=deleteservice&id=${service.getId()}"><span title="${manager.getString("services-page-table-span2")}" style="font-size:19px;padding-left: 7px; color:red" class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="myModalBox" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header" style="background-color: #30A5FF; color: white;">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 style="color:white;" class="modal-title">${manager.getString("modal-new-services-title")}</h4>
          </div>
          <div class="modal-body">
            <form method="POST" action="controller">
              <input type="hidden" name="action" value="addService" />
              <fieldset>
              <div class="form-group">
                <label for="name">${manager.getString("modal-new-services-label1")}:</label>
                <input type="text" class="form-control" id="name" required name="name" placeholder="${manager.getString("modal-new-services-input1")}:">
              </div>
              <div class="form-group">
                <label for="description">${manager.getString("modal-new-services-label2")}:</label>
                <textarea type="text" class="form-control" id="description" required name="description" placeholder="${manager.getString("modal-new-services-input2")}:"></textarea>
              </div>
              <div class="form-group">
                <label for="price">${manager.getString("modal-new-services-label3")}:</label>
                <input type="text" class="form-control" required id="price" name="price" placeholder="${manager.getString("modal-new-services-input3")}:">
              </div>
              <div class="form-group">
                  <div class="pull-right">
                    <button type="button" class="btn btn-default" data-dismiss="modal">${manager.getString("modal-new-services-button1")}:</button>
                    <button type="submit" class="btn btn-primary">${manager.getString("modal-new-services-button2")}:</button>
                  </div>
              </div>
              </fieldset>
            </form>
          </div>
        </div>
      </div>
    </div>
    
	<script>
            $('#price').bind("change keyup input click", function() {
    if (this.value.match(/[^0-9\.]/g)) {
        this.value = this.value.replace(/[^0-9\.]/g, '');
    }
});
            $(document).ready(function(){
            //при нажатию на любую кнопку, имеющую класс .btn
            $(".btn").click(function() {
            //открыть модальное окно с id="myModal"
            $("#myModal").modal('show');
            
            });
            });
		!function ($) {
			$(document).on("click","ul.nav li.parent > a > span.icon", function(){		  
				$(this).find('em:first').toggleClass("glyphicon-minus");	  
			}); 
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})
                
                $(function() {
                    $('#datetimepicker1').datepicker({
                        format: 'yyyy-mm-dd'
                    });
                });
	</script>	
</body>

</html>
