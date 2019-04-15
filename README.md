# EvolutionSim
Generator Ewolucyjny, university laboratory project
## Description
This project is simple simulation of life in closed enviroment.

We have rectangular map where plants can grow. Map is divided into jungle, and not jungle. Every day in both areas one new plant appears.

On this map, there are also animals. Male and female, with energy points and age. To survive they need to have more than 0 energy. Every day they lose energy, and when  they eat plants, they regain it (plant dies). When two adult animals meet on the same field on map, and have enough energy, they reproduce, creating new animal and loosing some energy. If animal is too old, it dies.

Animals move in random directories (one of 8).

Everything is visualised in terminal with "ascii art"
