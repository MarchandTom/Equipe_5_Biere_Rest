package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface CmdBDao {
    @SqlUpdate("CREATE TABLE cmdb(" + 
    		"	cno integer primary key autoincrement," +
    		"	uno integer," + 
    		"	qte integer" + 
    		");")
    void createCmdBTable();

    @SqlUpdate("insert into cmdb (uno, qte) values (:uno, :qte)")
    @GetGeneratedKeys
    int insert(@BindBean() CmdB cmdB);

    @SqlQuery("select * from cmdb where uno = :uno")
    @RegisterMapperFactory(BeanMapperFactory.class)
    CmdB findByUno(@Bind("uno") int uno);

    @SqlQuery("select * from cmdb where cno like :cno")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<CmdB> search(@Bind("cno") String cno);

    @SqlUpdate("drop table if exists cmdb")
    void dropCmdBTable();

    @SqlUpdate("delete from cmdb where cno = :cno")
    void delete(@Bind("cno") int cno);

    @SqlQuery("select * from cmdb order by uno")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<CmdB> all();

    @SqlQuery("select * from cmdb where cno = :cno")
    @RegisterMapperFactory(BeanMapperFactory.class)
    CmdB findByCno(@Bind("cno") int cno);

    void close();
}