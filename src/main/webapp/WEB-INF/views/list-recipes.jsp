<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Recipe Management</title>
	</head>
	<body>
	<div>
		<h2><a href="${pageContext.request.contextPath}/recipe/list">Recipe List</a></h2>

		<input type="button" value="Add Recipe" onclick="window.location.href='addRecipeForm'; return false;"/>
        <form:form action="search" method="POST">
			<div>
			    <div>
			      <div><i aria-hidden="true"></i></div>
			    </div>
			    <input type="text" placeholder="Search By Recipe Name" name="theSearchName">
			    <input type="submit" value="Search"/>
			</div>
        </form:form>
		<table border="1">
			<tr>
				<th>#</th>
				<th>Title</th>
				<th>Recipe Description</th>
				<th>Ingredients</th>
				<th>Cook Time (In Mins):</th>
				<th colspan="2">Action</th>
			</tr>
			<c:set var="index" value="0" />
			<c:forEach var="recipe" items="${recipes }">
				<c:url var="updateLink" value="/recipe/updateRecipeForm">
					<c:param name="recipeId" value="${recipe.id}"></c:param>
				</c:url>

				<c:url var="deleteLink" value="/recipe/delete">
					<c:param name="recipeId" value="${recipe.id}"></c:param>
				</c:url>

				<c:set var="index" value="${index + 1}" />
				<tr>
					<td>${index}</td>
					<td>${recipe.title}</td>
					<td>${recipe.description}</td>
					<td>${recipe.ingredients}</td>
					<td>${recipe.cookTime} Mins</td>
					<td>
						<!-- display the update link -->
						<a href="${updateLink}">Update</a>
					</td>
					<td>
						<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this recipe?'))) return false">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</body>
</html>