#### Lemouton Alexis INF3FA

# Rapport : Programmation Avancée Séance + TP 4 + notes


### Séance 13 décembre

###### Futur : quand une tâche asynchrone est terminée et qu'elle sert dans la suite du code, elle doit être mise à jour avant de continuer dans le code.
Manière d'expliquer les dépendances entre les tâches

###### Opération atomique : type d’opération qui s’exécute sans interruption, garantissant qu’elle est réalisée en une seule unité indivisible

###### Weak scaling : is defined as how the solution time varies with the number of processors for a fixed problem size per processor. Sp ~= 1



> #### Consigne assigment 102 + pi :
>
> assignement 102 : paramètres ntot et nb processor
>  pi : calculer ntot/p avec p = nbworker

> ##### Comparer les résultats :
>
>Vérifier que les sorties des deux codes sont identiques (errors, piapprox, piexact, Ntot, nbprocess, tmpDuree)
>Sauvegarder les sorties dans des fichiers txt : out_ass102_salle4c.txt pour assignement_102 et out_pi_salle_4c.txt pour pi


> ##### Analyser le speedup :
>
> Vérifier si le code se comporte comme prévu ✅
> Observer l’évolution du temps d’exécution avec l’augmentation du nombre de processeurs (ou workers)