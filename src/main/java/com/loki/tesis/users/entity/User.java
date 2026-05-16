package com.loki.tesis.users.entity;

import com.loki.tesis.shared.address.entity.Address;
import com.loki.tesis.users.enums.SocialNumberType;
import com.loki.tesis.users.enums.StateAccount;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    // ID privado para la base de datos. Es auto-incremental para facilidad en la busqueda, inserciòn, entre otras.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID publico no coincide con la base de datos para seguridad y busqueda.
    @UuidGenerator
    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private SocialNumberType tipoDocumento;

    @Column(unique = true, nullable = false, length = 11)
    private String numeroDocumento;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, length = 255)
    private String password;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private StateAccount estado = StateAccount.ACTIVE;

    // Datos de contacto
    @Column(length = 20)
    private String numeroTelefono;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Embedded
    private Address direccion;

}
