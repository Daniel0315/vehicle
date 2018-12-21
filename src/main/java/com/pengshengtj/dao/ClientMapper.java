package com.pengshengtj.dao;

import com.pengshengtj.popj.Client;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClientMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client
     *
     * @mbg.generated
     */
    List<Client> selectAll();

    int insertClient(Client client);

    Client selectClient(@Param("ownid") Integer ownid);

    int updateClient(Client client);

    int deleteClient(@Param("ownid") Integer ownid);
}