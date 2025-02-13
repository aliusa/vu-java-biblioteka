# Objektinis programavimas Java


## Pirmosios programos užduotis:

### Studentas pasirinkęs kokią nori dalykinę sritį turi parašyti programą, kuri susidėtų iš šių komponentų:

* bent iš trijų klasių, kurių kiekviena turi turėti bent kelis laukus, bent kelis privačius ir bent kelis viešus metodus;<br/>
`BooksItem::getLendedNowCount()`
* bent vienos klasės bent vienas laukas turi būti kitos klasės tipo; bent vienas šios klasės metodas turi tą lauką naudoti;<br/>
`BooksItem::getLendedNowCount()`
* bent viena klasė turi turėti bent vieną statinį lauką; šis laukas turi būti kur nors kode naudojamas;<br/>
`Database::FILE_NAME`
* bent viena klasė turi turėti bent vieną konstantą; ši konstanta turi būti kur nors kode naudojama;<br/>
`Database::FILE_NAME`
* bent viena klasė turi turėti bent vieną statinį metodą; šis metodas turi būti kur nors kode naudojamas;<br/>
`Database::getList()`
* turi būti bent vienas pavyzdys perkrauto metodo;<br/>
`UsersItem::toString()`
* kurioje nors klasėje turi būti statinis main metodas, demonstruojantis programos veikimą;<br/>
`Main::main()`
* visas kodas turi priklausyti kuriam nors vienam paketui;<br/>
`lt.alius.biblioteka`


## Antrosios programos užduotis:

### Studentas pasirinkęs kokią nori dalykinę sritį (gali tęsti tą pačią iš pirmosios užduoties arba pasirinkti kitą) turi parašyti programą, kuri susidėtų iš šių komponentų:

* bent vieno interfeiso, kuris turėtų bent du (abstrakčius) metodus ir kurį įgyvendintų bent dvi klasės;<br/>
`Identifiable` -> `UsersItem`, `AdministratorsUser`
* bent vienos abstrakčios klasės, turinčios bent vieną abstraktų metodą, iš kurios paveldėtų bent dvi konkrečios (ne abstrakčios) klasės (gali būti tos pačios, kurios įgyvendina interfeisą, arba kitos);<br/>
`DatabaseWrapper` -> `JsonWrapper`, `SqliteWrapper`
* turi būti perrašytas bent vienas metodas, kuris taip pat yra implementuotas (ne abstraktus) ir tėvinėje klasėje;<br/>
`BaseEntity::getId()`
* kurioje nors klasėje turi būti statinis main metodas, demonstruojantis programos veikimą;<br/>
`Main::main()`
* minėtame main metode turi būti naudojami interfeiso ar abstrakčios klasės tipo kintamieji, išskyrus tas vietas, kur būtina naudoti konkrečios klasės kintamuosius;<br/>
`Main::main` - `BooksItem::getId()`
* visas kodas turi priklausyti kuriam nors vienam paketui;<br/>
`lt.alius.library`


## Trečiosios programos užduotis:

### Studentas pasirinkęs kokią nori dalykinę sritį (gali tęsti tą pačią iš pirmųjų užduočių arba pasirinkti kitą) turi parašyti programą, kuri turėtų šias savybes:

* programa naudotų Java konteinerių klases (java.util.Collection implementuojančias klases ir paveldinčius interfeisus); visur, kur įmanoma, turi būti naudojami interfeisai; konkrečios klasės gali būti naudojamos tik kuriant konteinerius arba jei to reikalauja programos specifika;<br/>
`ArrayList` implementuoja `Collection'ą`
`Collection::add()` naudoja `Database.java`
* programoje turi būti aprašyta bent viena tikrinama išimtis (checked Exception); šią išimtį kurti ir mesti turi viena klasė, o gaudyti ir apdoroti kita;<br/>
`JsonWrapper::add()` -> `Database::add()` -> `Main::main()`
* programoje turi būti interfeisas, kurio bent vienas metodas turi deklaruoti metamą tikrinamą išimtį; bent viena šio metodo implementacija turi turėti realią galimybę šią išimtį mesti ir bent viena šio metodo implementacija turi nemesti ir nedeklaruoti šios išimties;<br/>
`DataStorage.java add()` interfeise throw'ina - `JsonWrapper.java` meta išimtį
* programoje turi būti panaudotas try sakinys su catch bloku, kuris turėtų realią galimybę išimtį pagauti;<br/>
`JsonWrapper::add()`, `Main.java`
* programoje turi būti panaudotas try sakinys su finally bloku;<br/>
`Main.java`

## Ketvirtosios programos užduotis:

### Studentas pasirinkęs kokią nori dalykinę sritį (gali tęsti tą pačią iš pirmųjų užduočių arba pasirinkti kitą) turi parašyti programą, kuri susidėtų iš šių komponentų:

* bent vieno sąrašo (enum) ar įrašo (record);<br/>
`BookQuality` enum
* įgyvendintų bent vieną projektavimo šabloną<br/>
Singleton - `Database`
* naudoti komandine eilute paduotus parametrus<br/>
`Main.java`
* naudotų bent dvi iš šių Java kalbos galimybių<br/>
* generines klases (generics): programa turi ne tik jas naudoti, bet ir aprašyti bent vieną klasę, kuri turėtų tipo kintąmąjį ir bent vieną metodą, kuris turėtų tipo kintamąjį, kuris nebūtų klasės kintamasis;<br/>
`EntityArrayList::toArray()` -> `Main.java`
* įvedimo/išvedimo srautai: programa turi turėti galimybę skaityti duomenis iš failo ir juos rašyti į failą;<br/>
`JsonWrapper.java` iš/į `database.json`
* funkcinio programavimo elementai: programa turi naudoti lambda skaičiavimų sintaksę ir srautų objektus (java.util.Stream);<br/>
`Main.java`
* daugiagijiškumas: programoje turi būti kuriamos bent kelios papildomos gijos, jos turi vienu metu naudoti bendrą atmintį ir naudodamos ją rakinti;<br/>
  ...
* grafinė vartotojo sąsaja: programa turi turėti paprastą vartotojo sąsają iš bent kelių prasmingai veikiančių mygtukų, ir bent kelių tekstą atvaizduojančių elementų<br/>
  ...
