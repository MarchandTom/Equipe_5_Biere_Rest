package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.ArrayList;
import java.util.List;

public interface UtilisateurDao {


	@SqlUpdate("create table utilisateur ("
			+ "uno integer primary key autoincrement, "
			+ "nom text, "
			+ "prenom text, "
			+ "enseigne text, "
			+ "siret text, "
			+ "email text, "
			+ "mdp text, "
			+ "adresse text, "
			+ "tel text, "
			+ "type text)")
	void createUtilisateurTable();

    @SqlUpdate("insert into utilisateur (nom,prenom,enseigne, siret, email, mdp, adresse, tel, type) values (:nom, :prenom, :enseigne, :siret, :email, :mdp, :adresse, :tel, :type)")
    @GetGeneratedKeys
    int insert(@BindBean() Utilisateur utilisateur);

    @SqlQuery("select * from utilisateur where nom = :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Utilisateur findByNom(@Bind("nom") String nom);

    @SqlQuery("select * from utilisateur where type like :type")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Utilisateur> search(@Bind("type") String type);

    @SqlUpdate("drop table if exists utilisateur")
    void dropUtilisateurTable();

    @SqlUpdate("delete from utilisateur where uno = :uno")
    void delete(@Bind("uno") int uno);

    @SqlQuery("select * from utilisateur order by uno asc")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Utilisateur> all();

    @SqlQuery("select * from utilisateur where uno = :uno")
    @RegisterMapperFactory(BeanMapperFactory.class)
	Utilisateur findById(@Bind("uno") int uno);void close();

    @SqlQuery("select * from utilisateur where email = :email and mdp = :mdp")
    @RegisterMapperFactory(BeanMapperFactory.class)
	Utilisateur findByLog(@Bind("email") String email,@Bind("mdp") String mdp);
}
