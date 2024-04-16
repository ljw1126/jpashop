package com.example.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;

    // 연관관계 주인은 Orders이므로, Orders의 member 지정, 읽기만 가능
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Embedded
    private Period period;

    @Embedded
    private Address homeAddress;

    // homeAddress 속성명 중복 -> @AttributeOverride로 커스텀화 가능
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column=@Column(name = "work_city")),
            @AttributeOverride(name = "street", column=@Column(name = "work_street")),
            @AttributeOverride(name = "zipcode", column=@Column(name = "work_zipcode"))
    })
    private Address workAddress;

    // 값 타입 컬렉션, 기존 LAZY
    // favorite_food 테이블에 food_name 속성, member_id 속성 가짐
    @ElementCollection
    @CollectionTable(name = "favorite_food", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "food_name")
    private Set<String> favoriteFoods = new HashSet<>();


    /*@ElementCollection
    @CollectionTable(name = "address", joinColumns = @JoinColumn(name = "member_id"))
    private List<Address> addressHistory = new ArrayList<>();*/

    // 값 컬렉션 대신 일대다 관계 활용
    // 값 컬렉션을 엔티티로 승격함으로써 식별자를 가지고 변경 이력 추적 가능해짐 (필드 추가 가능)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "member_id")
    private List<AddressEntity> addressHistory = new ArrayList<>();
}
