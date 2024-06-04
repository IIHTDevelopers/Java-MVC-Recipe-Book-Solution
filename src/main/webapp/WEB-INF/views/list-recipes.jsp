<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.yaksha.training.recipe.entity.RecipeStatus" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Recipe Management</title>
	</head>
	<body>
		<h2>Recipe List</h2>

		<input type="button" value="Add Recipe" onclick="window.location.href='addRecipeForm'; return false;"/>
        <form:form action="search" method="POST">
			    <input type="text" placeholder="Search By Recipe Name" name="theSearchName" value="${theSearchName}">
			    <input type="submit" value="Search"/>
        </form:form>
		<table border="1">
			<tr>
				<th>S No.</th>

				<th>Title
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=title,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=title"> Asc </a>
				</th>

				<th>Recipe Description
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=description,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=description"> Asc </a>
				</th>

				<th>Ingredients</th>
				<th>Cook Time (In Mins):</th>

				<th>Expert Review
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=status,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=status"> Asc </a>
				</th>

				<th colspan="2">Action</th>
			</tr>
			<c:set var="index" value="${page * 5 + 1}" />
			<c:forEach var="recipe" items="${recipes }">
				<c:url var="updateLink" value="/recipe/updateRecipeForm">
					<c:param name="recipeId" value="${recipe.id}"></c:param>
				</c:url>

				<c:url var="deleteLink" value="/recipe/delete">
					<c:param name="recipeId" value="${recipe.id}"></c:param>
				</c:url>

				<tr>
					<td>${index}</td>
					<td>${recipe.title}</td>
					<td>${recipe.description}</td>
					<td>${recipe.ingredients}</td>
					<td>${recipe.cookTime} Mins</td>
					<td>${recipe.status}</td>
					<td>
						<!-- display the update link -->
						<a href="${updateLink}">Update</a>
					</td>
					<td>
						<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this recipe?'))) return false">Delete</a>
					</td>
					<c:set var="index" value="${index + 1}" />
					<c:if test="${recipe.status eq RecipeStatus.PENDING}">
					<td><a href="/updateStatus?id=${recipe.id}&status=APPROVED">Approve</a></td>
					<td><a href="/updateStatus?id=${recipe.id}&status=REJECTED">Reject</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		        <br><br>
                	<c:choose>
                        <c:when test="${totalPage == 0}">
                            No Record Found
                        </c:when>
                        <c:otherwise>
                            <c:forEach begin="0" end="${totalPage-1}" varStatus="loop">
                                    &nbsp &nbsp<a href="/search?page=${loop.index}&size=5&theSearchName=${theSearchName}&theSearchDate=${theSearchDate}&sort=${sortBy}">${loop.index + 1}</a></li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

	</body>
</html>