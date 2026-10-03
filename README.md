# Recipe Recommendation App — Spring Boot Project

A REST API that answers a genuinely useful question: **"What can I cook
with the ingredients I already have?"** Built to teach Spring Boot's
standard layers (**Controller → Service → Repository → Model**), a
proper many-to-many relationship with extra data, DTOs, and a real
matching algorithm — not just CRUD.

## Why this is more than basic CRUD

- **Recipe** and **Ingredient** are linked through **RecipeIngredient**,
  a join entity that also stores a quantity per pairing (e.g. "2 cups"
  of flour just for this recipe). This is the standard way to model
  many-to-many relationships that need extra data.
- **DTOs** (`RecipeRequest`, `IngredientQuantityRequest`,
  `RecommendationRequest`) decouple the API's input shape from the
  database entities — the client sends ingredient *names*, and the
  service layer resolves or creates the matching `Ingredient` rows
  behind the scenes.
- **`RecommendationService`** is the centerpiece: given a list of
  ingredients you have, it scores every recipe by how many of its
  required ingredients you already own, and returns them ranked
  best-match-first, along with exactly what you're missing.

## What's inside

```
recipe-app/
├── pom.xml
├── recipe-app-postman-collection.json
└── src/
    ├── main/
    │   ├── java/com/example/recipeapp/
    │   │   ├── RecipeAppApplication.java     # App entry point
    │   │   ├── model/                         # Recipe, Ingredient, RecipeIngredient
    │   │   ├── dto/                            # Request/response shapes for the API
    │   │   ├── repository/                     # Database access (JpaRepository)
    │   │   ├── service/                         # CRUD + the recommendation algorithm
    │   │   ├── controller/                       # HTTP endpoints
    │   │   └── exception/                         # Custom errors + global handler
    │   └── resources/
    │       ├── application.properties
    │       └── static/index.html               # Dashboard frontend
    └── test/
        └── java/.../RecipeAppApplicationTests.java
```

## Prerequisites

- Java 17+
- Maven
- No external database — H2 runs in-memory, inside the app.

## How to run it

1. Unzip, open a terminal inside `recipe-app`.
2. `mvn spring-boot:run`
3. App runs on **http://localhost:8080**

**In Eclipse:** File → Import... → Maven → Existing Maven Projects →
select the `recipe-app` folder → Finish. Then run
`RecipeAppApplication.java` as a Java Application.

## Use the dashboard

Open **http://localhost:8080**. Three tabs:

- **Find Recipes** — type ingredients you have, comma separated (e.g.
  `egg, cheese, onion`), and see every recipe you can make (or nearly
  make), sorted by match percentage, with missing ingredients called out.
- **Recipes** — add a recipe with a title, cuisine, difficulty, cook
  time, instructions, and a dynamic list of ingredient + quantity lines.
- **Ingredients** — view/add/delete the master ingredient list directly
  (recipes also auto-create ingredients they reference).

## Test it in Postman

Import `recipe-app-postman-collection.json` — three folders: **Recipes**,
**Ingredients**, **Recommendations**.

Suggested flow: **Create Recipe** (the sample creates a Veggie Omelette
needing egg, onion, bell pepper, cheese) → **Recommend Recipes** with
`["egg", "cheese", "onion"]` → you should see the omelette at 75% match,
missing "bell pepper".

## Sample requests

**Add a recipe**
```bash
curl -X POST http://localhost:8080/api/recipes \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Veggie Omelette",
    "cuisine": "American",
    "difficulty": "EASY",
    "cookingTimeMinutes": 10,
    "instructions": "Whisk eggs, add veggies, cook until set.",
    "ingredients": [
      {"ingredientName": "egg", "quantity": "3"},
      {"ingredientName": "onion", "quantity": "1/2"},
      {"ingredientName": "cheese", "quantity": "1/4 cup"}
    ]
  }'
```

**Ask what you can cook**
```bash
curl -X POST http://localhost:8080/api/recommendations \
  -H "Content-Type: application/json" \
  -d '{"availableIngredients": ["egg", "cheese", "onion"]}'
```

## Explore the database visually

While running, open **http://localhost:8080/h2-console**. Use:
- JDBC URL: `jdbc:h2:mem:recipedb`
- User Name: `sa`
- Password: *(leave blank)*

## Talking points for a presentation

- **Many-to-many with extra data**: explain why `RecipeIngredient` exists
  as its own entity instead of a plain `@ManyToMany` — it's the only way
  to attach a `quantity` to each pairing.
- **DTOs vs entities**: `RecipeRequest` accepts ingredient *names*; the
  entity graph is built behind the scenes. Good for explaining API design,
  not just database design.
- **The matching algorithm**: walk through `RecommendationService.recommend()`
  — normalizing to lowercase, using a `Set` for O(1) lookups, scoring by
  percentage, then tie-breaking by fewest missing ingredients.

## Deploying this for a week (free, public URL)

The project is already set up for this — H2 still runs locally for
development (nothing changes in Eclipse), and a separate `prod` profile
switches to PostgreSQL only when deployed.

1. **Push this project to a GitHub repository** (public or private — Render
   supports both).
2. **Create a free PostgreSQL database on [render.com](https://render.com):**
   New → PostgreSQL → free plan → Create. Copy the **Internal Database URL**,
   username, and password it gives you.
3. **Create a Web Service on Render**, connected to your GitHub repo:
   - Environment: **Java**
   - Build Command: `mvn clean install -DskipTests`
   - Start Command: `java -jar target/recipe-app-1.0.0.jar`
4. **In the Web Service's Environment tab, add these variables:**
   - `SPRING_PROFILES_ACTIVE` = `prod`
   - `DATABASE_URL` = the Postgres JDBC URL Render gave you (format:
     `jdbc:postgresql://<host>:<port>/<dbname>`)
   - `DATABASE_USERNAME` = your Postgres username
   - `DATABASE_PASSWORD` = your Postgres password
5. **Click Deploy.** Render builds with Maven and starts the jar. Watch the
   logs for `Started RecipeAppApplication`, then open the public URL it gives
   you (e.g. `https://recipe-app-xyz.onrender.com`).

**Free tier note:** the app sleeps after 15 minutes of no traffic and takes
30-50 seconds to wake back up on the next request — open the link a minute
before you need it if you're demoing live. It stays deployed and reachable
for as long as you want (a week, a month, indefinitely) without needing your
own computer on.

## Suggested next steps

- Let users mark favorite recipes.
- Add a "substitute ingredients" feature (e.g. butter ↔ margarine).
- Add pagination/sorting on `GET /api/recipes` for a bigger dataset.
- Add Swagger/OpenAPI UI for a live, interactive API doc page.
