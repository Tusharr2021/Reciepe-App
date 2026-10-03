-- ============================================================
-- Seed data: ingredients + recipes loaded on every startup
-- ============================================================

-- Ingredients
INSERT INTO ingredients (name) VALUES ('egg');
INSERT INTO ingredients (name) VALUES ('salt');
INSERT INTO ingredients (name) VALUES ('pepper');
INSERT INTO ingredients (name) VALUES ('flour');
INSERT INTO ingredients (name) VALUES ('milk');
INSERT INTO ingredients (name) VALUES ('butter');
INSERT INTO ingredients (name) VALUES ('sugar');
INSERT INTO ingredients (name) VALUES ('olive oil');
INSERT INTO ingredients (name) VALUES ('garlic');
INSERT INTO ingredients (name) VALUES ('onion');
INSERT INTO ingredients (name) VALUES ('tomato');
INSERT INTO ingredients (name) VALUES ('cheese');
INSERT INTO ingredients (name) VALUES ('chicken');
INSERT INTO ingredients (name) VALUES ('rice');
INSERT INTO ingredients (name) VALUES ('pasta');
INSERT INTO ingredients (name) VALUES ('lemon');
INSERT INTO ingredients (name) VALUES ('cumin');
INSERT INTO ingredients (name) VALUES ('paprika');
INSERT INTO ingredients (name) VALUES ('baking powder');
INSERT INTO ingredients (name) VALUES ('vanilla extract');

-- Recipes
INSERT INTO recipes (title, cuisine, difficulty, cooking_time_minutes, instructions) VALUES
  ('Scrambled Eggs', 'American', 'EASY', 10,
   'Beat eggs with salt and pepper. Melt butter in pan, add eggs, stir gently until just set.');

INSERT INTO recipes (title, cuisine, difficulty, cooking_time_minutes, instructions) VALUES
  ('Pancakes', 'American', 'EASY', 20,
   'Mix flour, egg, milk, sugar, baking powder. Cook on buttered pan until golden.');

INSERT INTO recipes (title, cuisine, difficulty, cooking_time_minutes, instructions) VALUES
  ('Garlic Pasta', 'Italian', 'EASY', 20,
   'Cook pasta. Saute garlic in olive oil, toss with pasta, salt, and pepper.');

INSERT INTO recipes (title, cuisine, difficulty, cooking_time_minutes, instructions) VALUES
  ('Tomato Rice', 'Indian', 'EASY', 30,
   'Fry onion, garlic in oil. Add tomato, cumin, salt. Add rice and water, cook until done.');

INSERT INTO recipes (title, cuisine, difficulty, cooking_time_minutes, instructions) VALUES
  ('Butter Chicken', 'Indian', 'MEDIUM', 45,
   'Marinate chicken in spices. Cook in butter with tomato, garlic, onion, cumin, paprika, salt.');

INSERT INTO recipes (title, cuisine, difficulty, cooking_time_minutes, instructions) VALUES
  ('Cheese Omelette', 'French', 'EASY', 10,
   'Beat eggs with salt and pepper. Cook in butter, add cheese, fold and serve.');

INSERT INTO recipes (title, cuisine, difficulty, cooking_time_minutes, instructions) VALUES
  ('Lemon Butter Pasta', 'Italian', 'EASY', 25,
   'Cook pasta. Toss with butter, lemon juice, garlic, salt and pepper.');

INSERT INTO recipes (title, cuisine, difficulty, cooking_time_minutes, instructions) VALUES
  ('Vanilla Cake', 'American', 'MEDIUM', 60,
   'Mix flour, sugar, eggs, butter, milk, baking powder, vanilla. Bake at 180C for 35 min.');

-- recipe_ingredients: link each recipe to its ingredients
-- 1) Scrambled Eggs  (id=1): egg, salt, pepper, butter
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (1, 1, '3');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (1, 2, '1/2 tsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (1, 3, '1/4 tsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (1, 6, '1 tbsp');

-- 2) Pancakes (id=2): flour, egg, milk, sugar, baking powder, butter
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (2, 4, '1 cup');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (2, 1, '1');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (2, 5, '3/4 cup');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (2, 7, '2 tbsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (2, 19, '1 tsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (2, 6, '1 tbsp');

-- 3) Garlic Pasta (id=3): pasta, garlic, olive oil, salt, pepper
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (3, 15, '200g');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (3, 9, '4 cloves');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (3, 8, '3 tbsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (3, 2, '1 tsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (3, 3, '1/2 tsp');

-- 4) Tomato Rice (id=4): rice, onion, garlic, tomato, cumin, salt, olive oil
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (4, 14, '1 cup');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (4, 10, '1');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (4, 9, '2 cloves');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (4, 11, '2');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (4, 17, '1 tsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (4, 2, '1 tsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (4, 8, '2 tbsp');

-- 5) Butter Chicken (id=5): chicken, butter, tomato, garlic, onion, cumin, paprika, salt
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (5, 13, '500g');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (5, 6, '3 tbsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (5, 11, '3');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (5, 9, '4 cloves');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (5, 10, '1');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (5, 17, '1 tsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (5, 18, '1 tsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (5, 2, '1 tsp');

-- 6) Cheese Omelette (id=6): egg, cheese, butter, salt, pepper
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (6, 1, '2');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (6, 12, '30g');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (6, 6, '1 tbsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (6, 2, '1/4 tsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (6, 3, 'pinch');

-- 7) Lemon Butter Pasta (id=7): pasta, butter, lemon, garlic, salt, pepper
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (7, 15, '200g');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (7, 6, '2 tbsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (7, 16, '1');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (7, 9, '2 cloves');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (7, 2, '1 tsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (7, 3, '1/2 tsp');

-- 8) Vanilla Cake (id=8): flour, sugar, egg, butter, milk, baking powder, vanilla extract
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (8, 4, '2 cups');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (8, 7, '1 cup');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (8, 1, '3');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (8, 6, '100g');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (8, 5, '1 cup');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (8, 19, '2 tsp');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES (8, 20, '1 tsp');
