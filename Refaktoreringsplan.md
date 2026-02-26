### Refaktoreringsplan



##### Inkapsling av tillstånd

* Gör currentSpeed privat 
* Använd getters/setters för att ändra hastighet i Motor\_vehicle \& Scania 

Motivering: Detta förbättrar inkapslingen och säkerställer att objektets interna tillstånd inte kan manipuleras direkt av externa klasser.



##### Inför interface för specialfunktioner

* Skapa interface för tex turbo-och flakfunktionalitet som CarController behöver kunna styra
* Låt relevanta fordonsklasser implementera dessa interface.  
* Ändra CarController så att den arbetar mot dessa abstraktioner istället för konkreta subklasser och undvik direkt beroende via instanceof.

Motivering: På så sätt elimineras beroendet till specifika biltyper och ersätts med polymorfism, vilket minskar coupling och uppfyller Dependency Inversion Principle samt Open/Closed Principle.



##### Flytta bildansvar från DrawPanel

* Lägg till ett privat attribut i varje bilklass för dess bild (t.ex. BufferedImage image).
* Ladda in bilden vid objektets skapande.
* Skapa en getter-metod som returnerar bilens bild.
* Ändra DrawPanel så att den:
* hämtar bilden från varje bil via getter
* endast ansvarar för att rita bilden på rätt position

Motivering: Genom att flytta bildansvaret till respektive fordonsklass förbättras inkapslingen och ansvarsfördelningen i systemet. DrawPanel får ett renodlat ansvar att enbart rendera fordonens aktuella tillstånd, medan varje fordon ansvarar för sin egen visuella representation. Detta minskar kopplingen mellan vy och modell, följer Single Responsibility Principle och gör systemet mer utbyggbart enligt Open/Closed Principle.



##### Bryt ut kollisions- och regelhantering

* Skapa en separat klass (t.ex. CollisionHandler) som ansvarar för:
* Vägg Kollisioner
* Workshop-kollisioner
* Övriga simulerings regler
* Låt CarController enbart:
* Uppdatera fordonens rörelse
* Anropa kollisionshantering
* Uppdatera vyn

Motivering: Detta ökar cohesion genom att varje klass får ett tydligt och fokuserat ansvar. CarController får färre anledningar att förändras (SRP), och kopplingen mellan controller och affärsregler minskar. Systemet blir mer modulärt, testbart och enklare att vidareutveckla.



##### Optimera rendering

* Flytta repaint() utanför loopen i TimerListener.
* Uppdatera först alla fordons positioner och hantera kollisioner.
* Anropa därefter repaint() en gång per uppdateringscykel.

Motivering: Tydliggör separationen mellan uppdateringslogik och rendering (Separation of Concerns). Förhindrar onödiga omritningar, vilket förbättrar prestandan. Säkerställer att hela modellens tillstånd är uppdaterat innan vyn ritas om.





##### Factory method 

* Vi lägger till klassen Motor\_VehicleFactory och låter den ansvara för skapandet av bilarna, istället för att göra det direkt i CarController. 

Motivering: Sänker coupling till Volvo, Saab och Scania från CarController. Den behöver inte längre känna till de konkreta klasserna Saab och Scania. Bättre separation av ansvar (SRP)





##### Utförande:

Steg 1, 2 och 3 kan utföras parallellt eftersom de påverkar olika delar av systemet (modellinkapsling, specialfunktioner och bildresurser). Steg 4 bör göras efter att modellens API stabiliserats, och steg 5 kan göras när timerns loopstruktur är klar. Steg 3 måste vara klar innan DrawPanel kan använda getImage().



