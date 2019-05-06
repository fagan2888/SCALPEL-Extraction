package fr.polytechnique.cmap.cnam.etl.extractors.drugs.classification.families

import fr.polytechnique.cmap.cnam.etl.extractors.drugs.classification.{DrugClassConfig, PharmacologicalClassConfig}

object Antidepresseurs extends DrugClassConfig {

  val name = "Antidepresseurs"

  val cip13Codes: Set[String] = Set(
    "3400930820629",
    "3400931067627",
    "3400931067795",
    "3400935183644",
    "3400935216106",
    "3400934207839",
    "3400936098657",
    "3400930043523",
    "3400936883628",
    "3400935149466",
    "3400935650917",
    "3400934638589",
    "3400930043462",
    "3400930370155",
    "3400935325884",
    "3400936892613",
    "3400932448432",
    "3400934638640",
    "3400935479570",
    "3400936806917",
    "3400935180452",
    "3400935149695",
    "3400934207259",
    "3400935301413",
    "3400934638701",
    "3400935183583",
    "3400930532447",
    "3400932725618",
    "3400931022503",
    "3400931022961",
    "3400931022671",
    "3400930573198",
    "3400930572948",
    "3400930351635",
    "3400930573020",
    "3400930351574",
    "3400930573259",
    "3400930965030",
    "3400931260264",
    "3400933329266",
    "3400933329327",
    "3400931259725",
    "3400932500970",
    "3400931908869",
    "3400936401549",
    "3400933338602",
    "3400933086039",
    "3400933086329",
    "3400932028047",
    "3400931709787",
    "3400931402336",
    "3400932229130",
    "3400932224746",
    "3400932821280",
    "3400932306114",
    "3400936320482",
    "3400936889651",
    "3400936453647",
    "3400936745612",
    "3400936453418",
    "3400936320543",
    "3400936975910",
    "3400937938860",
    "3400938384949",
    "3400938386202",
    "3400938704631",
    "3400934855351",
    "3400939866512",
    "3400938704280",
    "3400938069044",
    "3400933100957",
    "3400938044201",
    "3400936118041",
    "3400949146888",
    "3400933604202",
    "3400935406941",
    "3400935102911",
    "3400936423480",
    "3400935406651",
    "3400935629166",
    "3400935639912",
    "3400935883766",
    "3400938071573",
    "3400939133560",
    "3400935630407",
    "3400935817013",
    "3400935739896",
    "3400937656603",
    "3400934505317",
    "3400935817181",
    "3400935732682",
    "3400936584211",
    "3400936238718",
    "3400949404674",
    "3400937715317",
    "3400935825117",
    "3400938327298",
    "3400935905970",
    "3400934776366",
    "3400935968555",
    "3400936453357",
    "3400935485724",
    "3400935983114",
    "3400936312500",
    "3400938385540",
    "3400936650886",
    "3400933833619",
    "3400936394063",
    "3400936743250",
    "3400938817164",
    "3400936472457",
    "3400936677272",
    "3400934653728",
    "3400921721942",
    "3400936677333",
    "3400936560666",
    "3400936168138",
    "3400930031353",
    "3400936059108",
    "3400936059047",
    "3400949179886",
    "3400936484634",
    "3400936393523",
    "3400936477131",
    "3400930002353",
    "3400938997682",
    "3400936887701",
    "3400936677623",
    "3400936167766",
    "3400922033167",
    "3400936394643",
    "3400937375344",
    "3400938461633",
    "3400936855182",
    "3400936953796",
    "3400937416719",
    "3400936486065",
    "3400937427135",
    "3400937426763",
    "3400936953857",
    "3400936974449",
    "3400937426992",
    "3400936854703",
    "3400941503405",
    "3400941503573",
    "3400935998712",
    "3400936772465",
    "3400935999313",
    "3400936392342",
    "3400936392113",
    "3400949815494",
    "3400937974240",
    "3400933525385",
    "3400937187329",
    "3400936011250",
    "3400936891081",
    "3400938099201",
    "3400933525217",
    "3400936011311",
    "3400936079618",
    "3400936687509",
    "3400938670479",
    "3400928005038",
    "3400936419810",
    "3400939340456",
    "3400936000834",
    "3400937427425",
    "3400936079557",
    "3400937795449",
    "3400934928604",
    "3400935982513",
    "3400937426473",
    "3400937417310",
    "3400939754000",
    "3400949490080",
    "3400949490431",
    "3400936945524",
    "3400937157513",
    "3400938027051",
    "3400937017145",
    "3400937016605",
    "3400937667487",
    "3400939985138",
    "3400934223228",
    "3400937324366",
    "3400936975569",
    "3400937320573",
    "3400934034190",
    "3400936980532",
    "3400935561817",
    "3400937324656",
    "3400936944282",
    "3400937708333",
    "3400936942912",
    "3400936979291",
    "3400937156561",
    "3400937324946",
    "3400936995321",
    "3400936975620",
    "3400935562067",
    "3400937120371",
    "3400936965393",
    "3400936980181",
    "3400936981362",
    "3400937319744",
    "3400937121033",
    "3400937016483",
    "3400937325257",
    "3400937319393",
    "3400936982482",
    "3400939752976",
    "3400937590853",
    "3400930064641",
    "3400936981652",
    "3400936982253",
    "3400938180664",
    "3400937693851",
    "3400936981942",
    "3400937456340",
    "3400936979703",
    "3400937694223",
    "3400937591393",
    "3400936026278",
    "3400932729111",
    "3400937850360",
    "3400936024267",
    "3400936713154",
    "3400935646835",
    "3400936026339",
    "3400933198312",
    "3400936713383",
    "3400935647085",
    "3400937850421",
    "3400935905161",
    "3400936024496",
    "3400935904270",
    "3400927851810",
    "3400927832710",
    "3400927911354",
    "3400935993748",
    "3400927808296",
    "3400927741548",
    "3400927740428",
    "3400927740596",
    "3400949000845",
    "3400935993977",
    "3400927851179",
    "3400927808708",
    "3400927827518",
    "3400935993519",
    "3400927911125",
    "3400927809187",
    "3400927831997",
    "3400927847097",
    "3400927852299",
    "3400922399980",
    "3400927740657",
    "3400922399003",
    "3400922394787",
    "3400927408472",
    "3400926881535",
    "3400926881764",
    "3400922394909",
    "3400926987084",
    "3400926987206",
    "3400935994110",
    "3400927827747",
    "3400927738937",
    "3400926987435",
    "3400927398094",
    "3400927398216",
    "3400927407871",
    "3400927402098",
    "3400926987374",
    "3400927428234",
    "3400927427282",
    "3400927428005",
    "3400927847509",
    "3400927556883",
    "3400926882884",
    "3400927558726",
    "3400927717246",
    "3400927738708",
    "3400927734854",
    "3400938204599",
    "3400927738586",
    "3400927574054",
    "3400927733734",
    "3400927574405",
    "3400927574283",
    "3400927573224",
    "3400927556944",
    "3400927715983",
    "3400927573682",
    "3400927427862",
    "3400927716645",
    "3400927559266",
    "3400927716065",
    "3400927717994",
    "3400927733383",
    "3400927718656",
    "3400927399794",
    "3400927718595",
    "3400927720666",
    "3400927733673",
    "3400927734106",
    "3400927911293",
    "3400927719837",
    "3400927719257",
    "3400927738647",
    "3400927734564",
    "3400927400896",
    "3400949000654",
    "3400927557835",
    "3400927749872",
    "3400927427343",
    "3400927809309",
    "3400922397450",
    "3400927851469",
    "3400927738876",
    "3400927926372",
    "3400927830068",
    "3400926987145",
    "3400927911064",
    "3400927809538",
    "3400927847158",
    "3400936428973",
    "3400927848049",
    "3400927847738",
    "3400930648742",
    "3400934428562",
    "3400936882508",
    "3400933324933",
    "3400936268463",
    "3400932155286",
    "3400936746213",
    "3400934833298",
    "3400934833359",
    "3400935736307",
    "3400935734174",
    "3400935736246",
    "3400935733924",
    "3400935735294",
    "3400935735584",
    "3400935734235",
    "3400935863195",
    "3400935835161",
    "3400936746442",
    "3400935738028",
    "3400935766403",
    "3400936618251",
    "3400935742919",
    "3400935743220",
    "3400935743398",
    "3400935738196",
    "3400936002036",
    "3400936001435",
    "3400935867049",
    "3400935834799",
    "3400936139381",
    "3400935737137",
    "3400936144705",
    "3400935766281",
    "3400936618480",
    "3400935834911",
    "3400935736994",
    "3400935737366",
    "3400935737885",
    "3400935766571",
    "3400935866738",
    "3400936132818",
    "3400936132177",
    "3400935735416",
    "3400935736017",
    "3400936002494",
    "3400936139213",
    "3400936139091",
    "3400936144583",
    "3400936144644",
    "3400936132238",
    "3400936618541",
    "3400936746381",
    "3400933707378",
    "3400933423797",
    "3400933423858",
    "3400932611782",
    "3400932799428",
    "3400930043738",
    "3400930043745",
    "3400932696659",
    "3400932150434",
    "3400931970033",
    "3400933082765",
    "3400922085845",
    "3400930036716",
    "3400934454127",
    "3400937614498",
    "3400937673860",
    "3400937725422",
    "3400939121093",
    "3400937726481",
    "3400937846349",
    "3400937846578",
    "3400935995230",
    "3400938106565",
    "3400938461114",
    "3400938461282",
    "3400938729825",
    "3400921843750",
    "3400938759112",
    "3400939022239",
    "3400939023410",
    "3400939024240",
    "3400937726313",
    "3400936946583",
    "3400932933914",
    "3400933525675",
    "3400930004494",
    "3400926752392",
    "3400926722418",
    "3400926722357",
    "3400930014202",
    "3400933736415",
    "3400934656330",
    "3400934655678",
    "3400930022047",
    "3400930022030",
    "3400922427997",
    "3400927367168",
    "3400922481128",
    "3400941685415",
    "3400922430669",
    "3400922480237",
    "3400933736064",
    "3400934656798",
    "3400934692758",
    "3400939131788",
    "3400939108148",
    "3400937429375",
    "3400938537376",
    "3400938199017",
    "3400938721898",
    "3400938199536",
    "3400938202649",
    "3400938203189",
    "3400938605884",
    "3400938408874",
    "3400938550214",
    "3400938444605",
    "3400938446784",
    "3400938535365",
    "3400938448795",
    "3400938534825",
    "3400938534306",
    "3400938534535",
    "3400938537666",
    "3400938537895",
    "3400938606317",
    "3400938607437",
    "3400938607208",
    "3400938757620",
    "3400938811940",
    "3400938756449",
    "3400938450637",
    "3400938783414",
    "3400938727814",
    "3400938722499",
    "3400938722901",
    "3400938826500",
    "3400938842586",
    "3400938725803",
    "3400938728583",
    "3400938812480",
    "3400938757101",
    "3400938761870",
    "3400938762303",
    "3400938775198",
    "3400938810189",
    "3400937429955",
    "3400938813081",
    "3400938814262",
    "3400938823486",
    "3400938826098",
    "3400934693359",
    "3400938827040",
    "3400938832532",
    "3400938841527",
    "3400938942514",
    "3400930018668",
    "3400938942743",
    "3400938948547",
    "3400939064451",
    "3400939081199",
    "3400939081601",
    "3400939082202",
    "3400939111049",
    "3400939125756",
    "3400930018675",
    "3400934661822",
    "3400939126937",
    "3400939132501",
    "3400939137292",
    "3400939137582",
    "3400941687426",
    "3400949610969",
    "3400949615353",
    "3400949696086",
    "3400949697618",
    "3400941714603",
    "3400941938818",
    "3400926986193",
    "3400922086385",
    "3400926986605",
    "3400934197741",
    "3400934198571",
    "3400941714313",
    "3400930018552",
    "3400930018590",
    "3400930020647",
    "3400930020746",
    "3400930020760",
    "3400930026571",
    "3400930026793",
    "3400930031704",
    "3400930031711",
    "3400930034521",
    "3400930020739",
    "3400930072905",
    "3400930034576",
    "3400930071724",
    "3400930071731",
    "3400930072899",
    "3400936586451",
    "3400936586512",
    "3400937023757",
    "3400949511464",
    "3400930031698",
    "3400930020609",
    "3400939433059",
    "3400927708312",
    "3400927708251",
    "3400927708190",
    "3400927708022"
  )

  val trycicliques = new PharmacologicalClassConfig(
    name = "Antidepresseurs_Tricycliques",
    ATCCodes = List("N06AA*"),
    ATCExceptions = List("N06AA06")
  )

  val isrs = new PharmacologicalClassConfig(
    name = "Antidepresseurs_ISRS",
    ATCCodes = List("N06AB*")
  )

  val isrsN = new PharmacologicalClassConfig(
    name = "Antidepresseurs_ISRSN",
    ATCCodes = List("N06AX11", "N06AX16", "N06AX17", "N06AX21", "N06AX26")
  )

  val imaoAB = new PharmacologicalClassConfig(
    name = "Antidepresseurs_IMAO_AB",
    ATCCodes = List("N06AF*")
  )

  val imaoA = new PharmacologicalClassConfig(
    name = "Antidepresseurs_IMAO_A",
    ATCCodes = List("N06AG*")
  )

  val autres = new PharmacologicalClassConfig(
    name = "Antidepresseurs_Autres",
    ATCCodes = List("N06AX03", "N06AX09", "N06AX14", "N06AX22", "N06AA06")
  )

  val all = new PharmacologicalClassConfig(
    name = "Antidepresseurs_All_by_rule",
    ATCCodes = List("N06A*"),
    ATCExceptions = List("N06AX01")
  )

  val pharmacologicalClasses = List(
    trycicliques,
    isrs,
    isrsN,
    imaoAB,
    imaoA,
    autres
  )

}