#### Lemouton Alexis INF3FA

# Rapport : Programmation Avancée Séance + TP 1, 2 et 3

### Introduction

Durant les quatre séances de programmation avancée, nous avons avancé dans nos travaux en utilisant l’IDE IntelliJ, 
un outil qui simplifie le développement et facilite l’organisation des différentes étapes. 
Pour mieux décomposer les séances et les TP, nous avons également travaillé avec Git, ce qui nous a permis de découper
les tâches en plusieurs versions, rendant le projet plus simple à reprendre et à continuer.

<hr>

### Mise en pratique

Avant de commencer les TP, nous avons suivi le cours et abordé les thématiques de paradigme et de framework.
Le paradigme se définit comme un ensemble de principes, de théories, de méthodes et de valeurs qui définissent une 
approche spécifique pour comprendre et expliquer un phénomène, orientant ainsi notre manière d'aborder la programmation. 
Quant au framework, il s’agit d’un ensemble d’outils réunis dans une sorte de "méga IDE" constitué de bibliothèques, 
fournissant une structure et des fonctionnalités prêtes à l'emploi pour accélérer le développement.

On a aussi vu les architectures des ordinateurs présents dans la salle G26 de l'IUT ainsi que l'architecture de nos 
téléphones.

L'architecture des ordinateurs de l'IUT est constituée comme suit :  
* Ordinateur : Dell Inc. OptiPlex 7050  
* Carte graphique : Intel HD Graphics 630  
* Processeurs : 8 Core I7-7700 3.60GHz  
* Moniteur : Generic Monitor (DELL P2417H)  
* Disque : SanDisk X400 512GB  
* RAM : 32Go.

L'architecture de mon téléphone est constituée de : 
* Model : Exynos 9611
* Cores : 8
* Architecture : 4x ARM Cortex-A73 2,31 GHz
* Architecture : 4x ARM Cortex-A53 1,74 GHz


On peut en déduire que les ordinateurs de la salle sont plus puissant que mon téléphone.

> **Application Séquentielle** : Exécute les instructions de manière linéaire, souvent sur un seul processeur (mono-threading).  
>
> **Application Parallèle** : Plusieurs tâches s’exécutent en parallèle, nécessitant une synchronisation pour assurer la cohérence des données. Exemple : calcul parallèle d’une somme de vecteurs.

- Un **thread** est un processus léger qui partage la même zone mémoire avec d'autres threads, mais qui dispose de son 
propre environnement et de sa propre pile. Il est caractérisé par son environnement, son état ou encore son nom.

<hr>


### TP 1
Le TP 1 vise à simuler le déplacement de mobiles dans une fenêtre graphique. 
Le langage de programmation utilisé est le Java.
Ce TP se base sur l'utilisation de threads. 

#### Exercice 1
L'exercice 1 consiste en la création et l'affichage d'un mobile grâce à JPanel. Cette classe permet de créer des widgets
ou bien des fenêtres graphiques où l'on peut dessiner et écrire.

La classe `UnMobile` hérite de JPanel et elle implémente l'interface Runnable afin de créer un objet graphique capable 
de se déplacer dans la zone délimitée par la fenêtre. Elle initialise les dimensions de la zone de déplacement en 
indiquant la largeur, la hauteur et l'emplacement de départ du mobile. De plus, elle définit aussi la vitesse de 
déplacement, le pas ainsi que le côté du mobile, ces champs sont marqués "final" afin de bloquer leur modification.

Ensuite la méthode `run` représente la méthode principale du projet. Elle lance une boucle infinie dans laquelle 
le ou les mobile(s) se déplace(nt). Les mobiles sont ensuite actualisés par la boucle à chaque étape.  


La classe `UneFenetre` hérite, elle aussi, de JFrame et contient une instance de UnMobile.
Dans le constructeur de `UneFenetre`, un mobile est ajouté, un thread est créé pour gérer l'animation du mobile et la 
fenêtre est affichée. Ce thread est ensuite démarré pour initier le mouvement.

Ensuite, nous devons faire en sorte que le mobile change de direction lorsqu’il touche les bords gauche et droit 
de la fenêtre.

#### Exercice 2
Dans cet exercice, nous devions ajouter un bouton afin de contrôler les mobiles. J'ai essayé d'ajouter le bouton pour 
stopper l'exécution, mais l'ajout ne fonctionnait pas donc je l'ai mis en commentaire.


#### Exercice 3
L'exercice 3 permet d'ajouter plusieurs mobiles grâce à l'utilisation d'un GridLayout. 
Avec cet ajout, il est possible d'insérer des mobiles en lignes et en colonnes.

<hr>

### TP2

Le TP2 introduit de la gestion de thread à l'aide de sémaphores. Il explique le fait de pouvoir bloquer l'entrée d'une 
pile d'instruction afin de laisser passer une seule instruction ou plusieurs selon comment il a été instancié.

Pour en revenir au projet, j'ai implémenté cette solution afin de bloquer des parties de l'exécution de la boucle.
Dans l'exécution de la méthode run, le mobile parcourt maintenant 6 étapes. Aux étapes 2 et 5, le mobile entre dans une 
partie appelée **ressource critique**.  

En effet, grâce aux sémaphores, on peut spécifier combien de threads peuvent être exécuté en même temps. Pour définir 
la zone de blocage, il suffit d'utiliser les méthodes `syncWait()` et `syncSignal()` de la classe `SemaphoreBinaire`.

`SemaphoreBinaire` est une classe qui hérite de la classe `Semaphore` qui est une classe abstraite. 
La méthode `syncWait()` permet de bloquer l'exécution des instructions et la méthode `syncSignal()` permet de laisser 
place à un autre thread qui, à son tour déclenche la méthode `syncWait()`.

> Ce fonctionnement garanti le suivi des instructions, mais peut retarder la vitesse d'exécution de tout un package.
[UML TP2](./uml_TP2.mdj)
### TP3

Le TP3 utilise un autre exemple de thread.  
Dans cet exercice, il y a 3 rôles majeurs : 
* le producteur de lettres
* la boite aux lettres
* le consommateur de lettres

Le producteur va envoyer une lettre dans la boite aux lettres qui peut posséder un nombre limité de lettres.
Le consommateur va récupérer les lettres qui sont dans la boite aux lettres. Quand cette dernière est vide, 
le consommateur ne pourra pas récupérer de lettre. Pour éviter qu'il aille toutes les fois à la boite aux lettres, on 
lui donne un tempo qui ralentit son processus.  
Ainsi, le producteur peut charger les lettres dans la boite. Lorsqu'il 
atteint la dernière lettre, il s'arrête et le consommateur fini d'ingérer les lettres.

> [UML TP3](./uml_TP3.mdj)