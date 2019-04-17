# geodata-generator
Geometric concept data generator for testing classification algorithms

It is used to create data sets representing  a geometric concept. The created data sets have two descriptive attributes corresponding to the two coordinates of a cartesian point, and a class attribute which is true if the point lie inside the geometric concept and false otherwise. A number of geometric concepts, with different boundary complexities, are available to be modeled. 

It can be used to study classification algorithms performance, data complexity characterization, and meta-learning among other machine learning topics. Particularly, it was used in:
- Tarragó, D.S., Herrera, F., Bello, R.: On the usefulness of Rough Sets Theory for Data Complexity: A case study on the domain of competence of C4.5. Central University Marta Abreu de Las Villas, Santa Clara, Cuba (2010). <a href="https://www.researchgate.net/publication/332470154_On_the_usefulness_of_Rough_Sets_Theory_for_Data_Complexity_A_case_study_on_the_domain_of_competence_of_C45">(text)</a>

Developed with:
- Java 1.8
- NetBeans IDE 8.2

Dependencies:
- Weka 3.7
- <a href="https://github.com/dstarrago/meta-learning">meta-learning project</a> (only for rough-set metric analisys)
