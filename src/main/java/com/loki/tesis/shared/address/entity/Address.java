package com.loki.tesis.shared.address.entity;

import com.loki.tesis.shared.address.enums.Provinces;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Column(length = 100)
    private String calle;

    @Column(length = 20)
    private String altura;

    @Column(length = 10)
    private String piso;

    @Column(length = 10)
    private String departamento;

    @Column(length = 100)
    private String entreCalleUno;

    @Column(length = 100)
    private String entreCalleDos;

    @Column(length = 100)
    private String ciudad;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private Provinces provincia;

    @Column(length = 20)
    private String codigoPostal;

    @Column(length = 255)
    private String referencias;

}
