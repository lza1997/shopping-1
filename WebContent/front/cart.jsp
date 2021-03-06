<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="common/head.jsp" />

<script>
 
    // remove from cart 
    function removeFromCart(id){
    	 if (confirm("确认删除该商品?")) {
    		$("#"+id).hide(); //hide
    		 //call removeFromCart 
    		jQuery.ajax({
				type : "post",
				url : "orderJson_removeFromCart.action",
				dataType : 'text',
				data : {id:id},
				success : function(json) {

				 if (json == null && json == '') {
						alert("异步处理异常，返回值为空！");
				 }
				}
			});
    		
    		
    	 }
    }
 
 
 </script>


<body>
	<!-- Wrapper -->
	<div id="wrapper_sec">
		<!-- Header -->
		<!--navigation part   -->
		<jsp:include page="common/navigation.jsp" />
		<div class="clear"></div>
		<div id="crumb">
			<ul>
				<li><a href="index.action">首页</a>
				</li>
				<li class="last"><a href="#" class="colr bold">我的购物车</a>
				</li>
			</ul>
		</div>
		<div class="clear"></div>
		<!-- Content Section -->
		<div id="content_sec">
			<!-- Column 3 -->
			<div class="col3">
				<h4 class="colr bold lognhead upper">
					<span class="arrow">My Cart</span>
				</h4>
				<div class="shoppingcart">
					<div class="carttable">
						<p class="note">以下商品如果你不想购买，你可以加入收藏中。</p>
						<a href="#" class="buttonone right">收藏</a>
						<div class="clear"></div>
						<ul class="head colr">
							<li class="imag">商品图片</li>
							<li class="p_name">商品名称</li>
							<li class="u_price">商品单价</li>
							<li class="qty">商品数量</li>
							<li class="s_total">共计</li>
							<li class="remove">删除商品</li>
						</ul>

						<%
                      //java.util.List<CCommodity> userCommodityList=session.getAttribute("userCommodityList");
                    
                    
                    %>

						<s:if test="#session.userCommodityList!=null">
							<s:iterator value="#session.userCommodityList" status="status">
								<s:if test="#status.odd">
									<ul class="cartcontents" id="<s:property value="id"/>">

										<li class="imag"><a
											href="detail.action?commodity.id=<s:property value="id"/>"><img
												src="images/static1.gif" alt="" />
										</a>
										</li>
										<li class="p_name"><a
											href="detail.action?commodity.id=<s:property value="id"/>"><s:property
													value="commodityName" />
										</a>
										</li>
										<li class="u_price"><p class="colr">
												￥
												<s:property value="price" />
											</p>
										</li>

										<li class="qty"><input name="s" type="text"
											value="<%=session.getAttribute("commodityNum"+"<s:property value='id'/>") %>" />
										</li>
										<li class="s_total"><p class="colr">
												<s:property value="price" />
											</p>
										</li>
										<li class="remove"><a href="#"
											onclick="removeFromCart('<s:property value="id"/>')">删除</a>
										</li>
									</ul>

								</s:if>
								<s:else>

									<ul class="cartcontents grey" id="<s:property value="id"/>">
										<li class="imag"><a
											href="detail.action?commodity.id=<s:property value="id"/>"><img
												src="images/static2.gif" alt="" />
										</a>
										</li>
										<li class="p_name"><a
											href="detail.action?commodity.id=<s:property value="id"/>"><s:property
													value="commodityName" />
										</a>
										</li>
										<li class="u_price"><p class="colr">
												￥
												<s:property value="price" />
											</p>
										</li>
										<li class="qty"><input name="s" type="text" value="1" />
										</li>
										<li class="s_total"><p class="colr">
												<s:property value="price" />
											</p>
										</li>
										<li class="remove"><a href="#"
											onclick="removeFromCart('<s:property value="id"/>')">删除</a>
										</li>
									</ul>


								</s:else>


							</s:iterator>

						</s:if>
						<s:else>

							<font color="red" size="4">购物车为空</font>

						</s:else>

					</div>
					<div class="buttonssec">
						<div class="right">
							<p class="colr bold">总计 : $1850.00</p>
						</div>
						<div class="right">
							<a href="index.action" class="buttonone">继续购物</a> <a href="#"
								class="buttonone">结算</a>
						</div>
					</div>
					<div class="clear"></div>
					<div class="clear"></div>
					<div class="relateditems">
						<h6 class="bold colr relhead">Based on your selection, you
							may be interested in the following items.</h6>
						<a href="#" class="leftarrow">&nbsp;</a>
						<ul>
							<li>
								<div class="thumb">
									<a href="detail.html"><img src="images/rel1.gif" alt="" />
									</a>
								</div>
								<div class="desc">
									<h6>
										<a href="detail.html" class="colr bold">Lorum Ipsum Dolor</a>
									</h6>
									<div class="rating">
										<div class="stars">
											<a href="#">&nbsp;</a> <a href="#">&nbsp;</a> <a href="#">&nbsp;</a>
											<a href="#">&nbsp;</a> <a href="#" class="gry">&nbsp;</a>
										</div>
										<a href="#" class="reviews">1 Review(s)</a>
									</div>
									<div class="prices">
										<p class="newprice colr bold">$58.00</p>
										<p class="oldprice">$88.00</p>
									</div>
									<div class="cartse">
										<a href="cart.html" class="buttonone">Add to Cart</a> <a
											href="#" class="whishlist">&nbsp;</a> <a href="#"
											class="favo">&nbsp;</a>
									</div>
								</div></li>
							<li>
								<div class="thumb">
									<a href="detail.html"><img src="images/rel1.gif" alt="" />
									</a>
								</div>
								<div class="desc">
									<h6>
										<a href="detail.html" class="colr bold">Lorum Ipsum Dolor</a>
									</h6>
									<div class="rating">
										<div class="stars">
											<a href="#">&nbsp;</a> <a href="#">&nbsp;</a> <a href="#">&nbsp;</a>
											<a href="#">&nbsp;</a> <a href="#" class="gry">&nbsp;</a>
										</div>
										<a href="#" class="reviews">1 Review(s)</a>
									</div>
									<div class="prices">
										<p class="newprice colr bold">$58.00</p>
										<p class="oldprice">$88.00</p>
									</div>
									<div class="cartse">
										<a href="cart.html" class="buttonone">Add to Cart</a> <a
											href="#" class="whishlist">&nbsp;</a> <a href="#"
											class="favo">&nbsp;</a>
									</div>
								</div></li>
							<li class="last">
								<div class="thumb">
									<a href="detail.html"><img src="images/rel1.gif" alt="" />
									</a>
								</div>
								<div class="desc">
									<h6>
										<a href="detail.html" class="colr bold">Lorum Ipsum Dolor</a>
									</h6>
									<div class="rating">
										<div class="stars">
											<a href="#">&nbsp;</a> <a href="#">&nbsp;</a> <a href="#">&nbsp;</a>
											<a href="#">&nbsp;</a> <a href="#" class="gry">&nbsp;</a>
										</div>
										<a href="#" class="reviews">1 Review(s)</a>
									</div>
									<div class="prices">
										<p class="newprice colr bold">$58.00</p>
										<p class="oldprice">$88.00</p>
									</div>
									<div class="cartse">
										<a href="cart.html" class="buttonone">Add to Cart</a> <a
											href="#" class="whishlist">&nbsp;</a> <a href="#"
											class="favo">&nbsp;</a>
									</div>
								</div></li>
						</ul>
						<a href="#" class="rightarrow">&nbsp;</a>
						<div class="clear"></div>
					</div>
				</div>
			</div>
			<div class="clear"></div>

		</div>
		<div class="clear"></div>
		<!-- Footer -->
		<jsp:include page="common/bottom.jsp" />
		<div class="clear"></div>
	</div>
</body>
</html>
<s:debug />
