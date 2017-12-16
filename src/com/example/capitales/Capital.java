package com.example.capitales;

public class Capital {
int id;
String continente;
String pais = "";
String capital;


public Capital(int parseInt, String string, String string2) {
	this.id = parseInt;
	this.pais = string;
	this.capital = string2;
}

public Capital() {
	// TODO Auto-generated constructor stub
}

public Capital(String string, String string2) {
	this.pais = string;
	this.capital = string2;
}

public Capital(String c,String string, String string2) {
	this.continente = c;
	this.pais = string;
	this.capital = string2;
}

public Capital(int parseInt, String string, String string2, String string3) {
	this.id = parseInt;
	this.continente = string;
	this.pais = string2;
	this.capital = string3;
}

public String getPais() {
	return this.pais;
}

public String getCapital() {
	return this.capital;
}

public void setID(int parseInt) {
	this.id = parseInt;
}

public void setPais(String string) {
	this.pais = string;
}

public void setCapital(String string) {
	this.capital = string;
}

public String getContinente() {
	return this.continente;
}

public void setContinente(String string) {
	this.continente = string;
}

}
