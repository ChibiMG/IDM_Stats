# Projet JSON - Partie Statistique - IDM
## M2 MIAGE - Université Rennes 1 - Maud Garçon & Emmanuel Chauvel

Vous trouverez dans ce Git le code source ainsi que les documents csv et json utilisés pour réaliser les statistiques du projet principal d'IDM.

Lien du git principal : https://github.com/manuc352/IDM-Projet

### Description
Ce projet contient des dossiers contenants les fichiers json :
 - Les fichiers en entrée des compilers : json_original
 - Les fichiers en sortie du compiler pythons : json_python
 - Les fichiers en sortie du compiler java : json_java
 
 Il contient également un dossier contenants les fichiers csv permettant d'avoir la sortie des statistiques pour les 3 cas suivants :
  - Comparaison des jsons en sortie des deux compilers python et java : comparateur_java_python.csv
  - Comparaison des jsons en sortie du compiler python et les fichiers originaux : comparateur_python_original.csv
  - Comparaison des jsons en sortie du compiler java et les fichiers originaux : comparateur_java_original.csv
  
 Enfin, il contient le code source java permettant de réaliser ces statistiques.
 La classe "Main.java" est la classe principale et lance pour chaque cas la récupération des statistiques.
 La classe "Comparator.java" est une classe effectuant une comparaison recursive sur les noeuds des fichiers json afin de renvoyer le pourcentage de ressemblance entre ceux-ci.


