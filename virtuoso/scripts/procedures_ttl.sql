create procedure post_view_1_sparql (in postid int) {
  declare content, imagefile, creationdate long varchar;
  result_names(content, imagefile, creationdate);
  for (SPARQL SELECT ?con ?image (?dt - xsd:dateTime("1970-01-01T00:00:00.000+00:00")) * 1000 as ?date
      where {
      	    ?post snvoc:id ?:postid .
	    { {?post snvoc:content ?con } union { ?post snvoc:imageFile ?image }} .
	    ?post snvoc:creationDate ?dt .
      }
  ) do result ("con", "image", "date");
}

create procedure post_view_2_sparql (in postid int) {
  declare firstname, lastname, personid long varchar;
  result_names(personid, firstname, lastname);

  for ( SPARQL SELECT ?creator ?p_firstname ?p_lastname
        where {
	  ?post snvoc:id ?:postid .
	  ?post snvoc:hasCreator ?creator .
	  ?creator snvoc:firstName ?p_firstname .
	  ?creator snvoc:lastName ?p_lastname .
	}
  ) do result ( "creator", "p_firstname", "p_lastname");
}

create procedure post_view_3_sparql (in postid int) {
  declare forumuri, forumname, modperson, modfirstname, modlastname long varchar;
  result_names(forumuri, forumname, modperson, modfirstname, modlastname);

  for ( SPARQL SELECT *
        where {
	  ?post snvoc:id ?:postid .
       	  ?post snvoc:replyOf* ?orig.
  	  ?orig a snvoc:Post .
	  ?forum snvoc:containerOf ?orig .
	  ?forum snvoc:title ?title .
	  ?forum snvoc:hasModerator ?moderator .
	  ?moderator snvoc:firstName ?first .
	  ?moderator snvoc:lastName ?last .
	}
  ) do result ("forum", "title", "moderator", "first", "last");
}

create procedure post_view_4_sparql (in postid int) {
  declare origpostid, origpostcontent, creationdate, origautorid, origfirstname, origlastname, friendornot long varchar;
  result_names(origpostid, origpostcontent, creationdate, origautorid, origfirstname, origlastname, friendornot);

  for ( SPARQL SELECT ?comment ?content
                      (?dt - xsd:dateTime("1970-01-01T00:00:00.000+00:00")) * 1000 as ?date
      	       	      ?creator ?creatorfirstname ?creatorlastname
                      (exists {  ?creator snvoc:knows ?author} as ?knows)
        where {
	  ?post snvoc:id ?:postid .
	  ?post snvoc:hasCreator ?author .
	  ?comment snvoc:replyOf ?post .
	  ?comment snvoc:content ?content .
	  ?comment snvoc:creationDate ?dt .
	  ?comment snvoc:hasCreator ?creator .
	  ?creator snvoc:firstName ?creatorfirstname .
	  ?creator snvoc:lastName ?creatorlastname .
	}
	order by desc(3) 4
  ) do result ( "comment", "content", "date", "creator", "creatorfirstname", "creatorlastname", "knows");
}

-- TODO: placeid is missing because of
-- Unplaced predicate in select layout in sqldf.c:7149
create procedure person_view_1_sparql (in personid int) {
  declare firstname, lastname, gender, browserused, locationip, birthday, creationdate, placeid long varchar;
  result_names(firstname, lastname, gender, birthday, creationdate, locationip, browserused, placeid);

  for ( SPARQL SELECT ?p_firstname ?p_lastname ?p_gender
                      (?p_birthday - xsd:dateTime("1970-01-01T00:00:00.000+00:00")) * 1000 as ?p_bd
                      (?p_creationdate - xsd:dateTime("1970-01-01T00:00:00.000+00:00")) * 1000 as ?p_cd
                      ?p_locationip ?p_browserused ?p_placeid
	where {
	  ?person snvoc:id ?:personid .
	  ?person snvoc:firstName ?p_firstname .
	  ?person snvoc:lastName ?p_lastname .
	  ?person snvoc:gender ?p_gender .
	  ?person snvoc:birthday ?p_birthday .
	  ?person snvoc:creationDate ?p_creationdate .
	  ?person snvoc:locationIP ?p_locationip .
   	  ?person snvoc:isLocatedIn ?p_place .
 	  ?person snvoc:browserUsed ?p_browserused .
	}
  ) do result ( "p_firstname", "p_lastname", "p_gender", "p_bd", "p_cd", 
                "p_locationip", "p_browserused", "p_placeid");
}

create procedure person_view_2_sparql (in personid int) {
  declare posturi, content, imagefile, postcreationdate, origpost, origperson, origfirstname, origlastname long varchar;
  result_names(posturi, content, imagefile, postcreationdate, origpost, origperson, origfirstname, origlastname);

  for ( SPARQL SELECT ?post ?con ?image
                      (?cd - xsd:dateTime("1970-01-01T00:00:00.000+00:00")) * 1000 as ?cdate
      	       	      ?orig ?person1 ?firstn ?lastn
        where {
	  ?pers snvoc:id ?:personid .
	  ?post snvoc:hasCreator ?pers .
	  { {?post snvoc:content ?con } union { ?post snvoc:imageFile ?image }} .
	  ?post snvoc:creationDate ?cd .
	  ?post snvoc:replyOf* ?orig .
	  ?orig a snvoc:Post .
	  ?orig snvoc:hasCreator ?person1 .
	  ?person1 snvoc:firstName ?firstn .
	  ?person1 snvoc:lastName ?lastn .
	}
	order by desc(?cd)
	limit 10
  ) do result ("post", "con", "image", "cdate", "orig", "person1", "firstn", "lastn");
}

create procedure person_view_3_sparql (in personid int) {
  declare friendpersonid, friendfirstname, friendlastname, since long varchar;
  result_names(friendpersonid, friendfirstname, friendlastname, since);

  for ( SPARQL SELECT ?fr ?p_friendfirstname ?p_friendlastname
                      (?k_since - xsd:dateTime("1970-01-01T00:00:00.000+00:00")) * 1000 as ?k_s
        where {
	  ?person snvoc:id ?:personid .
	  ?person snvoc:knows ?tmp .
	  ?tmp snvoc:creationDate ?k_since .
	  ?tmp snvoc:hasPerson ?fr .
	  ?fr snvoc:firstName ?p_friendfirstname .
	  ?fr snvoc:lastName ?p_friendlastname .
	}
	order by desc(4) 1
  ) do result ("fr", "p_friendfirstname", "p_friendlastname", "k_s");
}

checkpoint;
