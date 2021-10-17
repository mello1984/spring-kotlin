package ru.butakov.springkotlin

import javax.persistence.*

@Entity
@Table(name = "cats")
data class Cat(

    @Column(name = "name")
    val name: String = "",

    @Column(name = "age")
    val age: Int = 0
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

}

