create database report;
use report;
create table  pumps (
pumpid varchar(255),
seller_name varchar(255),
segment varchar(255),
primary key(pumpid)
);

create table produit(
pumpid varchar(255),
nom varchar(255),
prix double,
opening_meter double,
closing_meter double
);