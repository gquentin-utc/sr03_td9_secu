package fr.utc.sr03.td9_secu.model;

public class User {
	
	private int id_user;
	private String login;
	private String mot_de_passe;
	private String nom;
	private String prenom;
	private String numero_compte;
	private String profil_user;
	private int solde_compte;
	
	public User () {	
	}
	
	public User (int id_user) {	
		this.id_user = id_user;
	}
	
	public User(int id_user,String login,String mot_de_passe,String nom,String prenom,String numero_compte,String profil_user, int solde_compte) {
		this.id_user = id_user;
		this.login = login;
		this.mot_de_passe = mot_de_passe;
		this.nom = nom;
		this.prenom = prenom;
		this.numero_compte = numero_compte;
		this.profil_user = profil_user;
		this.solde_compte = solde_compte;
	}
	
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getNumero_compte() {
		return numero_compte;
	}
	public void setNumero_compte(String numero_compte) {
		this.numero_compte = numero_compte;
	}
	
	public String getProfil_user() {
		return profil_user;
	}
	public void setProfil_user(String profil_user) {
		this.profil_user = profil_user;
	}
	
	public int getSolde_compte() {
		return solde_compte;
	}
	public void setSolde_compte(int solde_compte) {
		this.solde_compte = solde_compte;
	}
	
}
