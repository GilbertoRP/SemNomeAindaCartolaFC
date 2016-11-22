package com.SemNomeAindaCartolaFC.Athletes;

import com.SemNomeAindaCartolaFC.DB.DataIdentifiable;

import java.io.Serializable;

public class Status extends DataIdentifiable implements Serializable{

    public Integer id;
    public String name;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
