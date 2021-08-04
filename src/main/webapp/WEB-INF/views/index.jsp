<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>

<%@ include file="layout/header.jsp" %>
<!-- 대문 사진 캐롤러 -->
<div id="demo" class="carousel slide" data-ride="carousel" style="position:relative;margin-top: 2.5%;">

    <!-- Indicators -->
    <ul class="carousel-indicators">
        <li data-target="#demo" data-slide-to="0" class="active"></li>
        <li data-target="#demo" data-slide-to="1"></li>
        <li data-target="#demo" data-slide-to="2"></li>
    </ul>

    <!-- The slideshow -->
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="/image/welcome/example1.jpg" width="100%" height="400" alt="Los Angeles">
        </div>
        <div class="carousel-item">
            <img src="/image/welcome/example2.jpg" width="100%" height="400" alt="Chicago">
        </div>
        <div class="carousel-item">
            <img src="/image/welcome/example3.jpg" width="100%" height="400" alt="New York">
        </div>
    </div>

    <!-- Left and right controls -->
    <a class="carousel-control-prev" href="#demo" data-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </a>
    <a class="carousel-control-next" href="#demo" data-slide="next">
        <span class="carousel-control-next-icon"></span>
    </a>

</div>

<!-- 대문 -->
<div class="container-plex p-3 justify-content-center bg-dark"
     style="height: fit-content;margin-top:50px;margin-bottom:50px;background: linear-gradient(145deg, #575b5d, #252b2d);">
    <!-- 방명록 -->
    <div class="row">
        <div id="demo1" class="carousel slide col-sm-6" data-ride="carousel">

            <!-- Indicators -->
            <ul class="carousel-indicators">
                <li data-target="#demo1" data-slide-to="0" class="active"></li>
                <li data-target="#demo1" data-slide-to="1"></li>
                <li data-target="#demo1" data-slide-to="2"></li>
            </ul>

            <!-- The slideshow -->
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="card">
                        <div class="card-header">방명록 ex1</div>
                        <div class="card-body">내용 1</div>
                        <div class="card-footer">작성자 1</div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="card">
                        <div class="card-header">방명록 ex2</div>
                        <div class="card-body">내용 2</div>
                        <div class="card-footer">작성자 2</div>
                    </div>
                </div>
                <div class="carousel-item">
                    <div class="card">
                        <div class="card-header">방명록 ex3</div>
                        <div class="card-body">내용 3</div>
                        <div class="card-footer">작성자 3</div>
                    </div>
                </div>
            </div>

            <!-- Left and right controls -->
            <a class="carousel-control-prev" href="#demo1" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#demo1" data-slide="next">
                <span class="carousel-control-next-icon"></span>
            </a>
        </div>
        <!-- 새글 -->
        <div id="demo2" class="carousel slide col-sm-6" data-ride="carousel">

            <!-- Indicators -->
            <ul class="carousel-indicators">
                <li data-target="#demo2" data-slide-to="0" class="active"></li>
                <li data-target="#demo2" data-slide-to="1"></li>
                <li data-target="#demo2" data-slide-to="2"></li>
            </ul>

            <!-- The slideshow -->
            <div class="carousel-inner">
                <div class="carousel-item active text-center text-white">
                    <h1>최신글</h1>
                </div>
                <c:forEach var="board" items="${boards.content}">

                <div class="carousel-item">



                        <div class="card">
                            <div class="card-header"><a href="/board/${board.id}">제목: ${board.title} </a></div>
                            <div class="card-footer">작성자: ${board.user.nickname}</div>
                        </div>
                    </div>
                    </c:forEach>
                </div>

                <!-- Left and right controls -->
                <a class="carousel-control-prev" href="#demo2" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#demo2" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </a>
            </div>
        </div>
        <!-- 블로그 기능 소개글,업데이트 이력-->
        <div class="row border border-warning text-white" style="margin-top:20px;">
            <div class="col-sm-8 border border-info">
                <h1>블로그 소개</h1>
            </div>
            <div class="col-sm-4 border border-success"><h1>블로그 업데이트 목록 : Github api사용</h1></div>

        </div>


        <br/>
    </div>
    <%@ include file="layout/footer.jsp" %>

