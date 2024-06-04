<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Recipe Management</title>
	</head>
	<body>
	<div>
		<h2>Recipes</h2>
		<h3>Add Recipe</h3>
		<div>
			<div>
				<hr>
				<form:form action="saveRecipe" modelAttribute="recipe" method="POST">
					<div>
						<label for="title">Title: </label>
						<form:input path="title" name="title" />
						<form:errors path="title"/>
					</div>
                    <div>
						<label for="description">Recipe Description: </label>
						<form:textarea path="description" name="description" />
						<form:errors path="description"/>
					</div>
                    <div>
						<label for="ingredients">Ingredients: </label>
						<form:textarea path="ingredients" name="ingredients" />
						<form:errors path="ingredients"/>
					</div>
                    <div>
						<label for="cookTime">Cook Time (In Mins): </label>
						<form:input type="number" path="cookTime" name="cookTime" />
						<form:errors path="cookTime"/>
					</div>

					<input type="submit" value="Save"/>
				</form:form>
			</div>
		</div>
		<a href="${pageContext.request.contextPath}/recipe/list">Back to List</a>
	</div>
	</body>
</html>