package com.example.courseproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_tag_mapping",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "item_user_likes_mapping",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likes;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private ItemCollection itemCollection;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Comment> comments;


    @Column(name = "custom_string_1")
    private String customString1;

    @Column(name = "custom_string_2")
    private String customString2;

    @Column(name = "custom_string_3")
    private String customString3;


    @Column(name = "custom_integer_1")
    private Integer customInteger1;

    @Column(name = "custom_integer_2")
    private Integer customInteger2;

    @Column(name = "custom_integer_3")
    private Integer customInteger3;


    @Column(name = "custom_boolean_1")
    private Boolean customBoolean1;

    @Column(name = "custom_boolean_2")
    private Boolean customBoolean2;

    @Column(name = "custom_boolean_3")
    private Boolean customBoolean3;


    @Column(name = "custom_multilinetext_1", length = 2000)
    private String customMultilineText1;

    @Column(name = "custom_multilinetext_2", length = 2000)
    private String customMultilineText2;

    @Column(name = "custom_multilinetext_3", length = 2000)
    private String customMultilineText3;


    @Column(name = "custom_date_1")
    private LocalDate customDate1;

    @Column(name = "custom_date_2")
    private LocalDate customDate2;

    @Column(name = "custom_date_3")
    private LocalDate customDate3;


    // Transient fields are used for fetching Latest items information

    @Transient
    private ItemCollection parentCollection;

}
