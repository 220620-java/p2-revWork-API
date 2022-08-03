
CREATE TABLE employer (
	id serial4 NOT NULL,
	"name" varchar(30) NULL,
	email varchar(50) NULL,
	username varchar(60) NOT NULL,
	"password" varchar(60) NOT NULL,
	CONSTRAINT employer_pkey PRIMARY KEY (id)
);


CREATE TABLE freelancer (
	id serial4 NOT NULL,
	"name" varchar(30) NULL,
	about varchar(300) NULL,
	experiencelevel varchar(20) NULL,
	email varchar(50) NULL,
	username varchar(60) NOT NULL,
	"password" varchar(60) NOT NULL,
	CONSTRAINT freelancer_pkey PRIMARY KEY (id)
);


CREATE TABLE public.openjobs (
	id serial4 NOT NULL,
	employerid int4 NULL,
	"name" varchar(30) NOT NULL,
	description varchar(300) NOT NULL,
	skills varchar(300) NULL,
	payrate varchar(60) NULL,
	CONSTRAINT openjobs_pkey PRIMARY KEY (id),
	CONSTRAINT openjobs_employerid_fkey FOREIGN KEY (employerid) REFERENCES public.employer(id)
);


CREATE TABLE public.profiles (
	id serial4 NOT NULL,
	freelancerid int4 NULL,
	college varchar(60) NOT NULL,
	"name" varchar(60) NULL,
	email varchar(60) NULL,
	CONSTRAINT profiles_pkey PRIMARY KEY (id),
	CONSTRAINT profiles_freelancerid_fkey FOREIGN KEY (freelancerid) REFERENCES public.freelancer(id)
);


CREATE TABLE public.application (
	id serial4 NOT NULL,
	jobid int4 NULL,
	profileid int4 NULL,
	coverletter text NULL,
	"name" varchar(50) NULL,
	CONSTRAINT application_pkey PRIMARY KEY (id),
	CONSTRAINT application_jobid_fkey FOREIGN KEY (jobid) REFERENCES public.openjobs(id),
	CONSTRAINT application_profileid_fkey FOREIGN KEY (profileid) REFERENCES public.profiles(id)
);




