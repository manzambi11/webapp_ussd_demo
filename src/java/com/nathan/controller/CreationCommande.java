/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nathan.controller;

import com.nathan.beans.Client;
import com.nathan.beans.Commande;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manzambi.n
 */
@WebServlet(name = "CreationCommande", urlPatterns = {"/CreationCommande"})
public class CreationCommande extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {String nom = request.getParameter( "nomClient" );

        String prenom = request.getParameter( "prenomClient" );
        String adresse = request.getParameter( "adresseClient" );
        String telephone = request.getParameter( "telephoneClient" );
        String email = request.getParameter( "emailClient" );

        /* Récupération de la date courante */

        Date dt = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat();

        /* Conversion de la date en String selon le format défini */

        String date = sdf.format(dt);

        double montant;

        try {

            /* Récupération du montant */

            montant = Double.parseDouble( request.getParameter( "montantCommande" ) );

        } catch ( NumberFormatException e ) {

            /* Initialisation à -1 si le montant n'est pas un nombre correct */

            montant = -1;

        }

        String modePaiement = request.getParameter( "modePaiementCommande" );

        String statutPaiement = request.getParameter( "statutPaiementCommande" );

        String modeLivraison = request.getParameter( "modeLivraisonCommande" );

        String statutLivraison = request.getParameter( "statutLivraisonCommande" );


        String message;

        /*

         * Initialisation du message à afficher : si un des champs obligatoires

         * du formulaire n'est pas renseigné, alors on affiche un message

         * d'erreur, sinon on affiche un message de succès

         */

        if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() || montant == -1

                || modePaiement.isEmpty() || modeLivraison.isEmpty() ) {

            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creerCommande.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'une commande.";

        } else {

            message = "Commande créée avec succès !";

        }

        /*

         * Création des beans Client et Commande et initialisation avec les

         * données récupérées

         */

        Client client = new Client();
        client.setNom( nom );
        client.setPrenom( prenom );
        client.setAdresse( adresse );
        client.setTelephone( telephone );
        client.setEmail( email );


        Commande commande = new Commande();

        commande.setClient( client );
        commande.setDate( date );
        commande.setMontant( montant );
        commande.setModePaiement( modePaiement );
        commande.setStatutPaiement( statutPaiement );
        commande.setModeLivraison( modeLivraison );
        commande.setStatutLivraison( statutLivraison );

        /* Ajout du bean et du message à l'objet requête */

        request.setAttribute( "commande", commande );
        request.setAttribute( "message", message );

        /* Transmission à la page JSP en charge de l'affichage des données */

        this.getServletContext().getRequestDispatcher( "/afficherCommande.jsp" ).forward( request, response );
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
