#### Lemouton Alexis INF3FA

# Rapport : Programmation Avancée Séance + TP 4 + Notes

## Séance 13 décembre

### Futur

Quand une tâche asynchrone est terminée et qu'elle sert dans la suite du code, elle doit être mise à jour avant de continuer dans le code.  
**Manière d'expliquer les dépendances entre les tâches**

### Opération atomique

Type d’opération qui s’exécute sans interruption, garantissant qu’elle est réalisée en une seule unité indivisible.

### Weak scaling

Défini comme la variation du temps de solution en fonction du nombre de processeurs pour une taille de problème fixe par processeur. Sp ~= 1

## TP4

### Assignment102 + Pi

**Assignement 102 :** paramètres `ntot` et `nb processor`  
**Pi :** calculer `ntot/p` avec `p = nbworker`

#### Comparaison des résultats

- **Vérification** que les sorties de `Assignment102` et de `Pi` sont identiques
- **Sauvegarde** des sorties dans des fichiers textes : `out_ass102_salle4c.txt` pour `assignement_102` et `out_pi_salle_4c.txt` pour `pi`

Avec les valeurs :
- Approximatisation de la valeur de pi
- L'erreur relative
- Le nombre de workers
- Le nombre de processeurs disponibles
- Le temps d'exécution

## Séance 17 janvier

### Scalabilité forte

Effectuer plus rapidement les mêmes simulations.

### Scalabilité faible

Simuler des modèles plus grands ou plus détaillés sans augmenter la durée d'exécution.

![Schéma scalabilité](scalabilite.png)

## TP4 (suite)

### WorkerSocket

- **Attributs :** `port` et `isRunning`
- **Méthode :** `Main`

**Exemple de création de 2 workers sur 2 ports différents : 25545 et 25546**

> Création de 2 workers sur 2 ports différents : 25545 et 25546
>
> SOCKET = Socket[addr=/127.0.0.1,port=25545,localport=52258]
> SOCKET = Socket[addr=/127.0.0.1,port=25546,localport=52259]
> Client sent: 12565291
> Client sent: 12565594
>
> Pi : 3.141360625
> Error: 7.385699400846197E-5
>
> Ntot: 32000000
> Available processors: 2
> Time Duration (ms): 565
>
> 7.385699400846197E-5 32000000 2 565



![piMws.png](piScalabiliteForte.png)

Cette image illustre un diagramme de scalabilité forte.
En bleu pointillé : la scalabilité idéale.

En bleu, la scalabilité forte est exécuté très rapidement, et ne permet pas de bien mesurer. 
En effet, le speedup est proche de 1ms pour chaque processeur et donc ne permet pas une lecture précise.

En orange et vert, la scalabilite forte, où le nombre de processeurs augmente en fonction du temps de résolution.
Tout dépend de la taille de flèches lancées.

1. **Forte scalabilité** :
    - **Scénario idéal** : Dans un scénario idéal, doubler le nombre de processeurs réduit de moitié le temps de
      résolution.
    - **Représentation graphique** : Le graphique montre une courbe où le temps de résolution diminue au fur et à mesure
      que le nombre de processeurs augmente, en suivant idéalement une relation f(x) = x.


![piScalabiliteFaible](piScalabiliteFaible.png)

Cette image illustre un diagramme de scalabilité faible.
En bleu la scalabilite faible : où la taille du problème par processeur est fixe et le nombre de processeurs augmente. 
Arrivé à un certain nombre de processeurs, elle s'effondre.

2. **Faible scalabilité** :
    - **Scénario idéal** : Dans un scénario idéal, on fixe la taille du problème par processus
      et on augmente le nombre de processus.
      C'est-à-dire, la taille du problème augmente avec le nombre de processus.
    - **Représentation graphique** : Lorsque les ressources augmentent, les performances s'améliorent très faiblement puis
      elles atteignent un seuil. Une fois le seuil atteint, le système sature et les performances s’effondrent.


> Exemple de scalabilité :
> forte = 12000000 * 1 proc; 6000000 * 2 proc
> faible = 12000000 * 1 proc; 24000000 * 2 proc
>
> - faible 12000000 par proc
> - forte 12000000 divisé par le nombre de proc
