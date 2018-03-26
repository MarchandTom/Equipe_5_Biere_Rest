package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface BiereDao {
    @SqlUpdate("create table bieres ("
    		+ "bno integer primary key autoincrement, "
    		+ "nom text NOT NULL, "
    		+ "uno int not null, "
    		+ "pno int, "
    		+ "prix decimal, "
    		+ "forme text, "
    		+ "type text, "
    		+ "description text, "
    		+ "taille int, "
    		+ "origine text, "
    		+ "degre int, "
    		+ "amertume text)")
    void createBiereTable();

    @SqlUpdate("insert into bieres (nom,uno,pno,prix,forme,type,description,taille,origine, degre, amertume) values (:nom, :uno, :pno, :prix, :forme, :type, :description, :taille, :origine, :degre, :amertume)")
    @GetGeneratedKeys
    int insert(@BindBean() Biere biere);

    @SqlQuery("select * from bieres where bno = :bno")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Biere findByBno(@Bind("bno") int bno);
    
    @SqlQuery("select * from bieres where uno = :uno")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Biere> findByUno(@Bind("uno") int uno);
    
    @SqlQuery("select * from bieres where nom = :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Biere findByNom(@Bind("nom") String nom);

    @SqlQuery("select * from bieres where search like :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Biere> search(@Bind("nom") String nom);

    @SqlUpdate("drop table if exists bieres")
    void dropBiereTable();

    @SqlUpdate("delete from bieres where bno = :bno")
    void delete(@Bind("bno") int bno);

    @SqlQuery("select * from bieres order by bno")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Biere> all();

//    @SqlQuery("select * from bieres where bno = :bno")
//    @RegisterMapperFactory(BeanMapperFactory.class)
//    Biere findById(@Bind("bno") int bno);

    void close();
}
