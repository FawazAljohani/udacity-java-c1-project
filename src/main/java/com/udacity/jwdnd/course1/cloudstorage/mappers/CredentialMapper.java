package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO credentials (credentialid, url, username, key, password, userid) VALUES(#{credentialId}, #{url}, #{userName}, #{password}, #{key}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int createCredential(Credential credential);

    @Select("SELECT * FROM credentials WHERE credentialid = #{credentialId}")
    Credential getCredential(Integer credentialId);

    @Select("SELECT * FROM credentials WHERE userid = #{userId}")
    List<Credential> getAllCredentials(Integer userId);

    @Update("UPDATE credentials SET url = #{url}, username = #{userName}, password = #{password} WHERE credentialid = #{credentialId}")
    int updateCredential(Credential credential);

    @Delete("DELETE FROM credentials WHERE credentialid = #{credentialId}")
    int deleteCredential(Integer credentialId);

}
