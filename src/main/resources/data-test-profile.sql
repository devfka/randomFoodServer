/**
 * CREATE Script for init of DB
 */

--User Table
insert into recipe (recipe_id, recipe_name) values (1, 'sucuklu kuru fasulye');
insert into recipe (recipe_id, recipe_name) values (2, 'penne bolonez');
insert into recipe (recipe_id, recipe_name) values (3, 'manti');

insert into ingredient (ingredient_id, ingredient_name) values (1, 'sucuk');
insert into ingredient (ingredient_id, ingredient_name) values (2, 'kuru fasulye');
insert into ingredient (ingredient_id, ingredient_name) values (3, 'domates');
insert into ingredient (ingredient_id, ingredient_name) values (4, 'makarna');
insert into ingredient (ingredient_id, ingredient_name) values (5, 'sogan');
insert into ingredient (ingredient_id, ingredient_name) values (6, 'yogurt');
insert into ingredient (ingredient_id, ingredient_name) values (7, 'biber');

-- List<Long> selectedList = new ArrayList<>(Arrays.asList(1L, 2L));
-- List<Long> unselectedList = new ArrayList<>(Arrays.asList(5L, 6L, 3L));

insert into recipe_ingredient_matrix (id, recipe_id, ingredient_id, optional) values (1, 1, 1, 'Y');
insert into recipe_ingredient_matrix (id, recipe_id, ingredient_id, optional) values (2, 1, 2, 'Y');
insert into recipe_ingredient_matrix (id, recipe_id, ingredient_id, optional) values (3, 1, 3, 'N');
insert into recipe_ingredient_matrix (id, recipe_id, ingredient_id, optional) values (4, 1, 5, 'Y');
insert into recipe_ingredient_matrix (id, recipe_id, ingredient_id, optional) values (5, 2, 3, 'Y');
insert into recipe_ingredient_matrix (id, recipe_id, ingredient_id, optional) values (6, 2, 4, 'Y');
insert into recipe_ingredient_matrix (id, recipe_id, ingredient_id, optional) values (7, 2, 5, 'Y');
insert into recipe_ingredient_matrix (id, recipe_id, ingredient_id, optional) values (8, 3, 5, 'Y');
insert into recipe_ingredient_matrix (id, recipe_id, ingredient_id, optional) values (9, 3, 6, 'Y');
insert into recipe_ingredient_matrix (id, recipe_id, ingredient_id, optional) values (10, 3, 7, 'Y');



