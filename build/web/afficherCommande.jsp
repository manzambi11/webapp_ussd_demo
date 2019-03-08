<%-- 
    Document   : afficherCommande
    Created on : 28 févr. 2019, 10:31:09
    Author     : manzambi.n
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Nom : ${ commande.client.nom }</p>
        <p>Prénom : ${ commande.client.prenom }</p>
        <p>Adresse : ${ commande.client.adresse }</p>
        <p>Numéro de téléphone : ${ commande.client.telephone }</p>
        <p>Email : ${ commande.client.email }</p>
        <p>Commande</p>
        <p>Date  : ${ commande.date }</p> 
        <p>Montant  : ${ commande.montant }</p> 
        <p>Mode de paiement  : ${ commande.modePaiement }</p> 
        <p>Statut du paiement  : ${ commande.statutPaiement }</p> 
        <p>Mode de livraison  : ${ commande.modeLivraison }</p> 
        <p>Statut de la livraison  : ${ commande.statutLivraison }</p>
    </body>
</html>
