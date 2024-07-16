-- SELECT 'CREATE DATABASE store_db WITH ENCODING = ''UTF8'';' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'store_db')\gexec
------------------------------------------------------------------
--- Level 1 -> Product
------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS "candidates" (
    "id" UUID PRIMARY KEY DEFAULT(gen_random_uuid()) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "username" VARCHAR(100) NOT NULL,
    "email"  VARCHAR(100) NOT NULL,
    "password" VARCHAR(20) NOT NULL,
    "description" TEXT NOT NULL,
    "curriculum" varchar(255),
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS "companies" (
    "id" UUID PRIMARY KEY DEFAULT(gen_random_uuid()) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "username" VARCHAR(100) NOT NULL,
    "website"  VARCHAR(100) NOT NULL,
    "password" VARCHAR(20) NOT NULL,
    "description" TEXT NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS "jobs" (
   "id" UUID PRIMARY KEY DEFAULT(gen_random_uuid()) NOT NULL,
   "benefits" VARCHAR(255) NOT NULL,
   "description" TEXT NOT NULL,
   "company_id" UUID NOT NULL,
   "level" VARCHAR(100) NOT NULL,
   "created_at" TIMESTAMP NOT NULL,
   "updated_at" TIMESTAMP NOT NULL
);



------------------------------------------------------------------
--- Level 1 -> Group
------------------------------------------------------------------
-- ALTER TABLE "candidates" ADD UNIQUE (name, username);

------------------------------------------------------------------
-- INDEX
------------------------------------------------------------------

-- CREATE INDEX IF NOT EXISTS "idx_candidates_name_email" ON "candidates" (name, email);
CREATE INDEX IF NOT EXISTS "idx_candidates_name_email" ON "candidates" (name, email);
CREATE INDEX IF NOT EXISTS "idx_companies_name_website" ON "companies" (name, website);

------------------------------------------------------------------
-- FOREIGN KEY
------------------------------------------------------------------

ALTER TABLE "jobs" ADD CONSTRAINT "fk_jobs_company_id" FOREIGN KEY ("company_id") REFERENCES "companies" ("id") ON DELETE CASCADE;

--
--
-- ------------------------------------------------------------------
-- -- LIMPEZA DA BASE
-- ------------------------------------------------------------------
--
TRUNCATE TABLE candidates RESTART IDENTITY CASCADE;