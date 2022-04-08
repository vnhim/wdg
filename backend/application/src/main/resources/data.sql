insert into company_info(name) values ('wedoogift') ON CONFLICT DO NOTHING;


insert into user_info(first_name, last_name, company_id) values ('Monica', 'Lewinski', 1) ON CONFLICT DO NOTHING;
insert into user_info(first_name, last_name, company_id) values ('Liliane', 'Bettencourt', 1) ON CONFLICT DO NOTHING;

insert into company_credit_balance(company_id, balance)
    values (1, 1500) ON CONFLICT DO NOTHING;

insert into user_gift_deposit(user_id, deposit, start_date, end_date)
    values (1, 150, NOW(), NOW() + interval '1 year') ON CONFLICT DO NOTHING;
insert into user_gift_deposit(user_id, deposit, start_date, end_date)
    values (1, -20, NOW(), NOW() + interval '1 year') ON CONFLICT DO NOTHING;
insert into user_gift_deposit(user_id, deposit, start_date, end_date)
    values (1, 20, NOW() - interval '2 year', NOW() - interval '1 year') ON CONFLICT DO NOTHING;

insert into user_meal_deposit(user_id, deposit, start_date, end_date)
    values (1, 130.10, NOW(), NOW() + interval '1 year') ON CONFLICT DO NOTHING;
insert into user_meal_deposit(user_id, deposit, start_date, end_date)
    values (1, 30.80, NOW(), NOW() + interval '1 year') ON CONFLICT DO NOTHING;