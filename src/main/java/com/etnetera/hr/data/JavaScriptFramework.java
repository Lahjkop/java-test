package com.etnetera.hr.data;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;


/**
 * Simple data entity describing basic properties of every JavaScript framework.
 * 
 * @author Etnetera
 *
 */


@Entity
public class JavaScriptFramework {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
    @Length(max = 30)
    @Column(nullable = false, length = 30)
	private String name;

	@Length(max = 10)
    @Column(length = 10)
    private String version;

    @Column
    private String hypeLevel;

    @Column
    private LocalDate deprecationDate;

    public JavaScriptFramework(final String name) {
        this.name = name;
    }

    public JavaScriptFramework() {
    }

    public JavaScriptFramework(@NotNull @Length(max = 30) final String name, @Length(max = 10) final String version, final String hypeLevel,
            final LocalDate deprecationDate) {
        this.name = name;
        this.version = version;
        this.hypeLevel = hypeLevel;
        this.deprecationDate = deprecationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public String getHypeLevel() {
        return hypeLevel;
    }

    public void setHypeLevel(final String hypeLevel) {
        this.hypeLevel = hypeLevel;
    }

    public LocalDate getDeprecationDate() {
        return deprecationDate;
    }

    public void setDeprecationDate(final LocalDate deprecationDate) {
        this.deprecationDate = deprecationDate;
    }

    @Override
    public String toString() {
        return "JavaScriptFramework [id=" + id + ", name=" + name + "]";
    }



}
