DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE IF NOT EXISTS public.company_info (
	id bigserial NOT NULL,
	name VARCHAR(50) NOT NULL,
	CONSTRAINT company_info_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.user_info (
	id bigserial NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	company_id bigserial NOT NULL,
	CONSTRAINT user_info_pkey PRIMARY KEY (id),
	CONSTRAINT user_info_company_id_fkey FOREIGN KEY (company_id) REFERENCES company_info(id)
);

CREATE TABLE IF NOT EXISTS public.company_credit_balance (
	id bigserial NOT NULL,
	company_id bigserial NOT NULL,
	balance numeric DEFAULT 0,
	CONSTRAINT company_credit_balance_pkey PRIMARY KEY (company_id),
	CONSTRAINT company_credit_balance_company_id_unique_key UNIQUE (company_id),
	CONSTRAINT company_credit_balance_company_id_fkey FOREIGN KEY (company_id) REFERENCES company_info(id)
);

CREATE TABLE IF NOT EXISTS public.user_gift_deposit (
	id bigserial NOT NULL,
	user_id bigserial NOT NULL,
	deposit numeric NOT NULL,
	start_date timestamp NOT NULL,
	end_date timestamp NOT NULL,
	CONSTRAINT user_gift_deposit_pkey PRIMARY KEY (id),
	CONSTRAINT user_gift_deposit_user_id_fkey FOREIGN KEY (user_id) REFERENCES user_info(id)
);

CREATE TABLE IF NOT EXISTS public.user_meal_deposit (
	id bigserial NOT NULL,
	user_id bigserial NOT NULL,
	deposit numeric NOT NULL,
	start_date timestamp NOT NULL,
	end_date timestamp NOT NULL,
	CONSTRAINT user_meal_deposit_pkey PRIMARY KEY (id),
	CONSTRAINT user_meal_deposit_user_id_fkey FOREIGN KEY (user_id) REFERENCES user_info(id)
);