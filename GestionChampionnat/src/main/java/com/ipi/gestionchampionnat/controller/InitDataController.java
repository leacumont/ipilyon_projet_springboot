package com.ipi.gestionchampionnat.controller;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.time.LocalDate;

@Controller
public class InitDataController {

    //@Autowired
    public InitDataController(){}

    @PostConstruct
    private void init() {}

    /*private MembreService membreService;
    private CategorieService categorieService;
    private RecetteService recetteService;
    private IngredientService ingredientService;
    private CommentaireService commentaireService;*

    @Autowired
    public InitDataController(MembreService membreService,
                              CategorieService categorieService,
                              RecetteService recetteService,
                              IngredientService ingredientService,
                              CommentaireService commentaireService) {
        super();
        this.membreService = membreService;
        this.categorieService = categorieService;
        this.recetteService = recetteService;
        this.ingredientService = ingredientService;
        this.commentaireService = commentaireService;
    }

    @PostConstruct
    private void init() {
        membreService.supprimerTousMembre();
        categorieService.supprimerTousCategorie();
        ingredientService.supprimerTousIngredient();
        commentaireService.supprimerTousCommentaire();
        recetteService.supprimerTousRecette();

        //creation des 2 membres
        Membre membre1 = new Membre();
        membre1.setNom("Membre1");
        membre1.setPrenom("Membre1");
        membre1.setEmail("membre1@gmail.com");
        membre1.setDateInscription(Date.valueOf(LocalDate.now()));
        membre1.setMdp("membre1");
        membre1.setPseudo("membre1");
        membreService.ajouterMembre(membre1);

        Membre membre2 = new Membre();
        membre2.setNom("lea");
        membre2.setPrenom("lea");
        membre2.setEmail("lea@gmail.com");
        membre2.setDateInscription(Date.valueOf(LocalDate.now()));
        membre2.setMdp("lea");
        membre2.setPseudo("lea");
        membreService.ajouterMembre(membre2);

        //creation des 2 recettes
        Recette recette1 = new Recette();
        recette1.setTitre("Tartiflette");
        recette1.setDescription("La tartiflette savoyarde est un gratin de pommes de terre avec du Reblochon fondu dessus.");
        recette1.setAuthor("Nicolas");
        recette1.setPhoto("src/main/resources/static/img/tartiflette.jpg");
        recetteService.ajouterRecette(recette1);

        Recette recette2 = new Recette();
        recette2.setTitre("Velouté de carottes au cumin");
        recette2.setDescription("U velouté de carotte au cumin");
        recette2.setAuthor("Nicolas");
        recette2.setPhoto("src/main/resources/static/img/veloute-de-carotte-au-cumin.jpg");
        recetteService.ajouterRecette(recette2);

        //creation des ingrédients pour les 2 recettes
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setRecette(recette1);
        ingredient1.setNom("Pommes de terre");
        ingredient1.setQuantite(750);
        ingredient1.setUnit("g");
        ingredientService.ajouterIngredient(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setRecette(recette1);
        ingredient2.setNom("Reblochon");
        ingredient2.setQuantite(1);
        ingredient2.setUnit("u");
        ingredientService.ajouterIngredient(ingredient2);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setRecette(recette1);
        ingredient3.setNom("Lardons");
        ingredient3.setQuantite(200);
        ingredient3.setUnit("g");
        ingredientService.ajouterIngredient(ingredient3);

        Ingredient ingredient4 = new Ingredient();
        ingredient4.setRecette(recette1);
        ingredient4.setNom("Crème fraîche épaisse");
        ingredient4.setQuantite(3);
        ingredient4.setUnit("cs");
        ingredientService.ajouterIngredient(ingredient4);

        Ingredient ingredient5 = new Ingredient();
        ingredient5.setRecette(recette1);
        ingredient5.setNom("Oignons");
        ingredient5.setQuantite(2);
        ingredient5.setUnit("u");
        ingredientService.ajouterIngredient(ingredient5);

        Ingredient ingredient6 = new Ingredient();
        ingredient6.setRecette(recette1);
        ingredient6.setNom("Beurre");
        ingredient6.setQuantite(20);
        ingredient6.setUnit("g");
        ingredientService.ajouterIngredient(ingredient6);

        Ingredient ingredient7 = new Ingredient();
        ingredient7.setRecette(recette1);
        ingredient7.setNom("Sel");
        ingredient7.setQuantite(1);
        ingredient7.setUnit("cc");
        ingredientService.ajouterIngredient(ingredient7);

        Ingredient ingredient8 = new Ingredient();
        ingredient8.setRecette(recette1);
        ingredient8.setNom("Poivre");
        ingredient8.setQuantite(1);
        ingredient8.setUnit("p");
        ingredientService.ajouterIngredient(ingredient8);

        Ingredient ingredient9 = new Ingredient();
        ingredient9.setRecette(recette2);
        ingredient9.setNom("Carottes");
        ingredient9.setQuantite(800);
        ingredient9.setUnit("g");
        ingredientService.ajouterIngredient(ingredient9);

        Ingredient ingredient10 = new Ingredient();
        ingredient10.setRecette(recette2);
        ingredient10.setNom("Oignon");
        ingredient10.setQuantite(1);
        ingredient10.setUnit("u");
        ingredientService.ajouterIngredient(ingredient10);

        Ingredient ingredient11 = new Ingredient();
        ingredient11.setRecette(recette2);
        ingredient11.setNom("Bouillon de volaille");
        ingredient11.setQuantite(2);
        ingredient11.setUnit("u");
        ingredientService.ajouterIngredient(ingredient11);

        Ingredient ingredient12 = new Ingredient();
        ingredient12.setRecette(recette2);
        ingredient12.setNom("Cumin");
        ingredient12.setQuantite(1);
        ingredient12.setUnit("cs");
        ingredientService.ajouterIngredient(ingredient12);

        Ingredient ingredient13 = new Ingredient();
        ingredient13.setRecette(recette2);
        ingredient13.setNom("Crème fraîche épaisse");
        ingredient13.setQuantite(2);
        ingredient13.setUnit("cs");
        ingredientService.ajouterIngredient(ingredient13);

        Ingredient ingredient14 = new Ingredient();
        ingredient14.setRecette(recette2);
        ingredient14.setNom("Huile d’olive");
        ingredient14.setQuantite(2);
        ingredient14.setUnit("cs");
        ingredientService.ajouterIngredient(ingredient14);

        Ingredient ingredient15 = new Ingredient();
        ingredient15.setRecette(recette2);
        ingredient15.setNom("Sel");
        ingredient15.setQuantite(1);
        ingredient15.setUnit("cc");
        ingredientService.ajouterIngredient(ingredient15);

        Ingredient ingredient16 = new Ingredient();
        ingredient16.setRecette(recette2);
        ingredient16.setNom("Poivre");
        ingredient16.setQuantite(1);
        ingredient16.setUnit("p");
        ingredientService.ajouterIngredient(ingredient16);


        //creation des 2 catégories
        Categorie categorie1 = new Categorie();
        categorie1.setNom("Entrée");
        categorie1.getRecettes().add(recette1);

        Categorie categorie2 = new Categorie();
        categorie2.setNom("Plat principal");

        recette1.setCategorie(categorie1);
        recette2.setCategorie(categorie2);

        categorieService.ajouterCategorie(categorie1);
        categorieService.ajouterCategorie(categorie2);

        //creation des 2 commentaires
        Commentaire commentaire1 = new Commentaire();
        commentaire1.setAuteur("Julien");
        commentaire1.setContenu("Vraiment délicieux!");
        commentaire1.setDateCreation(Date.valueOf(LocalDate.now()));
        commentaire1.setNote(9);
        commentaire1.setRecette(recette1);
        commentaireService.ajouterCommentaire(commentaire1);

        Commentaire commentaire2 = new Commentaire();
        commentaire2.setAuteur("Patrick");
        commentaire1.setContenu("Pas très foufou");
        commentaire1.setDateCreation(Date.valueOf(LocalDate.now()));
        commentaire1.setNote(3);
        commentaire1.setRecette(recette2);
        commentaireService.ajouterCommentaire(commentaire2);
    }*/
}
