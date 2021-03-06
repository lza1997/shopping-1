<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="common/head.jsp" />

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
				<li class="last"><a href="#" class="colr bold">我的消费记录</a>
				</li>
			</ul>
		</div>
		<div class="clear"></div>
		<!-- Content Section -->
		<div id="content_sec">
			<!-- Column 1 -->
			<div class="col1">
				<!-- Shop By Price -->
				<div class="byprice">
					<h5 class="colr">MY CONSUMER RECORDS</h5>
					<ul>
						<li><a href="myAccount.action">我的订单</a>
						</li>
						<li><a href="myConsumerRecords.action">我的消费记录</a>
						</li>
						<li><a href="myRecentlyViewed.action">我最近浏览</a>
						</li>
						<li><a href="myFavorites.action">我的收藏夹</a>
						</li>
						<li><a href="myIntegral.action">我的积分</a>
						</li>
						<li><a href="myInfo.action">账户信息</a>
						</li>
						<li><a href="updateUserPassword.action">修改密码</a>
						</li>
					</ul>
				</div>
				<div class="clear"></div>
				<div class="clear"></div>
			</div>
			<!-- Column 2 -->
			<div class="col2">
				<!-- My Account -->
				<div class="account">
					<h4 class="colr bold smallheading upper">
						<span class="arrow">Recently Consumer Records</span>
					</h4>
					<div class="accinner">
						<div class="recentorders">
							<div class="account_table">
								<s:if test="consumerRecordsList.size>0">

									<ul class="headtable">
										<li class="orderCode colr bold">订单号</li>
										<li class="commodityCode colr bold">商品代码</li>
										<li class="commodityName colr bold">商品名称</li>
										<li class="ordertotal colr bold">金额</li>
										<li class="date colr bold">消费时间</li>
									</ul>
									<s:iterator value="consumerRecordsList" status="status">

										<s:if test="#status.even">
											<ul class="contable grey">
												<li class="orderCode"><a
													href="orderDetailByOrderCode.action?orderCode=<s:property value="orderCode"/>"><s:property
															value="orderCode" />
												</a>
												</li>
												<li class="commodityCode"><a
													href="detail.action?id=<s:property value="commodityId"/>"><s:property
															value="commodityCode" />
												</a>
												</li>
												<li class="commodityName"><a
													href="detail.action?id=<s:property value="commodityId"/>"><s:property
															value="commodityName" />
												</a>
												</li>
												<li class="ordertotal">￥<s:property value="amount" />
												</li>
												<li class="date"><s:property value="consumerTime" />
												</li>
											</ul>
										</s:if>
										<s:else>
											<ul class="contable">
												<li class="orderCode"><a
													href="orderDetailByOrderCode.action?orderCode=<s:property value="orderCode"/>"><s:property
															value="orderCode" />
												</a>
												</li>
												<li class="commodityCode"><a
													href="detail.action?id=<s:property value="commodityId"/>"><s:property
															value="commodityCode" />
												</a>
												</li>
												<li class="commodityName"><a
													href="detail.action?id=<s:property value="commodityId"/>"><s:property
															value="commodityName" />
												</a>
												</li>
												<li class="ordertotal">￥<s:property value="amount" />
												</li>
												<li class="date"><s:property value="consumerTime" />
												</li>
											</ul>
										</s:else>
									</s:iterator>
								</s:if>
								<s:else>
									<font class="myH5">暂无消费记录</font>
								</s:else>
							</div>
						</div>

						<div class="clear"></div>

						<div class="clear"></div>
					</div>
				</div>

			</div>
			<div class="clear"></div>
			<hr />

			<div class="relateditems">
				<h6 class="bold colr relhead">根据您的购买记录，我们为您推荐</h6>
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
									href="#" class="whishlist">&nbsp;</a> <a href="#" class="favo">&nbsp;</a>
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
									href="#" class="whishlist">&nbsp;</a> <a href="#" class="favo">&nbsp;</a>
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
									href="#" class="whishlist">&nbsp;</a> <a href="#" class="favo">&nbsp;</a>
							</div>
						</div></li>
				</ul>
				<a href="#" class="rightarrow">&nbsp;</a>
				<div class="clear"></div>

			</div>


		</div>
		<div class="clear"></div>
		<!-- Footer -->
		<jsp:include page="common/bottom.jsp" />
		<div class="clear"></div>
	</div>
</body>
</html>
