package fr.polytechnique.cmap.cnam.study.fall.codes

import fr.polytechnique.cmap.cnam.etl.extractors.drugs.{DrugClassConfig, PharmacologicalClassConfig}

object Neuroleptiques extends DrugClassConfig {

  val name = "Neuroleptiques"

  val cip13Codes: Set[String] = Set(
    "3400932043705",
    "3400930571477",
    "3400932722426",
    "3400930571187",
    "3400930571248",
    "3400930742983",
    "3400930742754",
    "3400930743126",
    "3400930742815",
    "3400932725786",
    "3400931303299",
    "3400931623359",
    "3400931919926",
    "3400932963898",
    "3400930681510",
    "3400930680919",
    "3400931214601",
    "3400930681169",
    "3400930681459",
    "3400931214540",
    "3400930681220",
    "3400932228010",
    "3400931187882",
    "3400931039013",
    "3400931038931",
    "3400930645550",
    "3400930645499",
    "3400930716670",
    "3400932725847",
    "3400930716960",
    "3400931869627",
    "3400935427182",
    "3400933155209",
    "3400933155377",
    "3400930657386",
    "3400930657508",
    "3400930657157",
    "3400930657447",
    "3400930657218",
    "3400931506744",
    "3400931419716",
    "3400931372318",
    "3400931404866",
    "3400931419136",
    "3400932635108",
    "3400932699322",
    "3400932376599",
    "3400930471432",
    "3400930471890",
    "3400930471661",
    "3400926921149",
    "3400932082506",
    "3400930471722",
    "3400931084655",
    "3400931300458",
    "3400930323199",
    "3400930335956",
    "3400931934653",
    "3400934339523",
    "3400931809654",
    "3400932634965",
    "3400931655527",
    "3400932947522",
    "3400932947980",
    "3400936954397",
    "3400932948062",
    "3400936954168",
    "3400932947751",
    "3400931672845",
    "3400931307204",
    "3400931307143",
    "3400931760993",
    "3400933435745",
    "3400933984281",
    "3400932334209",
    "3400932227709",
    "3400935704719",
    "3400935703828",
    "3400935514103",
    "3400935703996",
    "3400935771025",
    "3400935704078",
    "3400935770714",
    "3400935513670",
    "3400935513502",
    "3400935770592",
    "3400935513441",
    "3400927372070",
    "3400935514042",
    "3400935704658",
    "3400927371240",
    "3400927371301",
    "3400935770943",
    "3400927371189",
    "3400935770653",
    "3400927371998",
    "3400949990351",
    "3400949989690",
    "3400949988631",
    "3400949510344",
    "3400949510283",
    "3400949509744",
    "3400949509683",
    "3400949507382",
    "3400949507092",
    "3400949506552",
    "3400949505432",
    "3400949505371",
    "3400949505203",
    "3400949509515",
    "3400949505142",
    "3400949504251",
    "3400949504022",
    "3400949503940",
    "3400949454761",
    "3400949452811",
    "3400949447909",
    "3400949446728",
    "3400949433599",
    "3400949399840",
    "3400949399789",
    "3400949399611",
    "3400949399550",
    "3400949399499",
    "3400949399321",
    "3400949399031",
    "3400949398959",
    "3400949398720",
    "3400949398379",
    "3400949253968",
    "3400941879753",
    "3400941512858",
    "3400939898049",
    "3400941876332",
    "3400939897738",
    "3400939672861",
    "3400939668901",
    "3400939668031",
    "3400939666259",
    "3400939562940",
    "3400939671741",
    "3400939562889",
    "3400939561479",
    "3400939554785",
    "3400939545103",
    "3400939544441",
    "3400939529424",
    "3400939528762",
    "3400941511967",
    "3400939528304",
    "3400939479873",
    "3400922099972",
    "3400939513164",
    "3400939480244",
    "3400939454979",
    "3400939454689",
    "3400939454221",
    "3400939404882",
    "3400939404592",
    "3400939369952",
    "3400939371962",
    "3400939372273",
    "3400939370033",
    "3400939370323",
    "3400939368832",
    "3400939442853",
    "3400939368542",
    "3400939174952",
    "3400939174723",
    "3400939529882",
    "3400941879173",
    "3400941877162",
    "3400939554846",
    "3400938808179",
    "3400938808988",
    "3400938641905",
    "3400939563480",
    "3400938640892",
    "3400938639025",
    "3400938638073",
    "3400938637243",
    "3400938636413",
    "3400938820065",
    "3400938348460",
    "3400938348170",
    "3400938347401",
    "3400938347111",
    "3400939175324",
    "3400938346978",
    "3400937545730",
    "3400935537164",
    "3400935454386",
    "3400949398430",
    "3400939370491",
    "3400935454218",
    "3400939370613",
    "3400934712722",
    "3400934238017",
    "3400949399260",
    "3400934237874",
    "3400939402123",
    "3400934237706",
    "3400939371214",
    "3400939402581",
    "3400930016749",
    "3400930016732",
    "3400949434190",
    "3400927604362",
    "3400927603822",
    "3400949453870",
    "3400926827946",
    "3400939443744",
    "3400926827427",
    "3400949504190",
    "3400926827137",
    "3400939454511",
    "3400922437934",
    "3400939369204",
    "3400939454399",
    "3400922437064",
    "3400922415413",
    "3400949507443",
    "3400949507153",
    "3400922415291",
    "3400922414980",
    "3400939514284",
    "3400939527642",
    "3400922414751",
    "3400949930968",
    "3400922144498",
    "3400922144559",
    "3400939527932",
    "3400922144320",
    "3400922414690",
    "3400922100494",
    "3400939455051",
    "3400922080871",
    "3400922080062",
    "3400922079004",
    "3400921691115",
    "3400921693416",
    "3400949740581",
    "3400949740413",
    "3400949740352",
    "3400949739691",
    "3400930064528",
    "3400930064504",
    "3400930064467",
    "3400930055205",
    "3400930046579",
    "3400930046531",
    "3400949001170",
    "3400930046517",
    "3400930046340",
    "3400930045701",
    "3400930045695",
    "3400930045688",
    "3400930045671",
    "3400930045657",
    "3400930045640",
    "3400930045626",
    "3400930045602",
    "3400930045589",
    "3400930045411",
    "3400930045404",
    "3400930038864",
    "3400930038826",
    "3400930028537",
    "3400930028520",
    "3400930046333",
    "3400930028407",
    "3400930028414",
    "3400930028391",
    "3400930028438",
    "3400930028377",
    "3400930028322",
    "3400930046555",
    "3400949739523",
    "3400949740291",
    "3400930064481",
    "3400930010365",
    "3400930010389",
    "3400930010334",
    "3400930010310",
    "3400930028315",
    "3400930010266",
    "3400930010242",
    "3400927503726",
    "3400927503375",
    "3400930028490",
    "3400926965952",
    "3400930028513",
    "3400926965723",
    "3400926965433",
    "3400926965204",
    "3400936143173",
    "3400936021594",
    "3400935439420",
    "3400935439369",
    "3400935439130",
    "3400935153609",
    "3400935380425",
    "3400933651855",
    "3400933651794",
    "3400936143234",
    "3400935626325",
    "3400933651626",
    "3400932653546",
    "3400932653485",
    "3400932544684",
    "3400936021655",
    "3400932418107",
    "3400932417735",
    "3400932417674",
    "3400930329054",
    "3400930328804",
    "3400930328972",
    "3400930328743",
    "3400937976022",
    "3400937975889",
    "3400936617889",
    "3400936415218",
    "3400936394704",
    "3400936596627",
    "3400936094574",
    "3400933133955",
    "3400933318901",
    "3400933222826",
    "3400933130015",
    "3400932559992",
    "3400931742555",
    "3400931742494",
    "3400933374211",
    "3400933374389",
    "3400930067857",
    "3400934611094",
    "3400949357277",
    "3400949356676",
    "3400938645347",
    "3400938644746",
    "3400937107426",
    "3400936934467",
    "3400936933866",
    "3400936857193",
    "3400936856943",
    "3400936856073",
    "3400936855991",
    "3400936806856",
    "3400936806047",
    "3400936805675",
    "3400936754027",
    "3400936753945",
    "3400936742598",
    "3400936728813",
    "3400936728691",
    "3400936728462",
    "3400936728172",
    "3400936727922",
    "3400936721258",
    "3400936654501",
    "3400936561267",
    "3400936558076",
    "3400936742420",
    "3400936499942",
    "3400936447493",
    "3400936447264",
    "3400936446953",
    "3400936806108",
    "3400936443471",
    "3400936442412",
    "3400936442290",
    "3400936424951",
    "3400936381056",
    "3400936361584",
    "3400936856714",
    "3400936264731",
    "3400936264441",
    "3400936239609",
    "3400936286443",
    "3400936237308",
    "3400936217027",
    "3400939385631",
    "3400935287014",
    "3400936361294",
    "3400934874802",
    "3400936393172",
    "3400934874741",
    "3400934736216",
    "3400936326118",
    "3400936424890",
    "3400933438586",
    "3400932844524",
    "3400949614172",
    "3400949612680",
    "3400949291069",
    "3400949290468",
    "3400949289349",
    "3400949175345",
    "3400941515002",
    "3400941514289",
    "3400939821337",
    "3400939819617",
    "3400939458472",
    "3400939457703",
    "3400939457413",
    "3400939339856",
    "3400939339047",
    "3400939180526",
    "3400939180236",
    "3400939176154",
    "3400939175782",
    "3400939175553",
    "3400939148120",
    "3400939147178",
    "3400939103235",
    "3400939102924",
    "3400939102863",
    "3400939063911",
    "3400939063799",
    "3400939063560",
    "3400939047881",
    "3400939047423",
    "3400939010632",
    "3400939005959",
    "3400938993202",
    "3400938993080",
    "3400938558197",
    "3400938557886",
    "3400938557718",
    "3400938429176",
    "3400938428407",
    "3400938427745",
    "3400938354904",
    "3400938346169",
    "3400938335453",
    "3400938335224",
    "3400938282313",
    "3400938282191",
    "3400938282023",
    "3400938281651",
    "3400938281422",
    "3400938281071",
    "3400938280999",
    "3400938280180",
    "3400938279870",
    "3400938279702",
    "3400938224061",
    "3400938218268",
    "3400938217667",
    "3400938207552",
    "3400938207491",
    "3400938207323",
    "3400938207033",
    "3400938206951",
    "3400938206890",
    "3400938207262",
    "3400938206722",
    "3400938206661",
    "3400938206432",
    "3400938195514",
    "3400938216608",
    "3400938195453",
    "3400938195392",
    "3400938226072",
    "3400938178944",
    "3400938178883",
    "3400938178715",
    "3400938178654",
    "3400938178593",
    "3400938178425",
    "3400938281880",
    "3400938178135",
    "3400938178074",
    "3400938177992",
    "3400938177824",
    "3400938177763",
    "3400938177534",
    "3400938177305",
    "3400938177244",
    "3400938334913",
    "3400938346510",
    "3400938177183",
    "3400938177015",
    "3400938176933",
    "3400938176872",
    "3400938161564",
    "3400938161274",
    "3400938160734",
    "3400938160383",
    "3400938992830",
    "3400939009742",
    "3400938160154",
    "3400938159783",
    "3400937990387",
    "3400937989909",
    "3400939048543",
    "3400937989039",
    "3400937988377",
    "3400937986427",
    "3400937985307",
    "3400937902380",
    "3400937902151",
    "3400937901840",
    "3400937760430",
    "3400939147697",
    "3400937759540",
    "3400937758420",
    "3400937751506",
    "3400937750783",
    "3400939175904",
    "3400939180755",
    "3400937747653",
    "3400937681964",
    "3400937681445",
    "3400937681094",
    "3400936815780",
    "3400936815322",
    "3400936663664",
    "3400936656802",
    "3400939824239",
    "3400936656512",
    "3400936374713",
    "3400936374362",
    "3400936373822",
    "3400949176007",
    "3400936249424",
    "3400936249363",
    "3400936249134",
    "3400934899416",
    "3400949610150",
    "3400934398483",
    "3400934398025",
    "3400933894870",
    "3400930044452",
    "3400930044445",
    "3400930044421",
    "3400934398193",
    "3400934427381",
    "3400933895013",
    "3400949500215",
    "3400949500192",
    "3400949500123",
    "3400949500116",
    "3400937329798",
    "3400936921740",
    "3400936921450",
    "3400936407862",
    "3400936407343",
    "3400936406971",
    "3400930059708",
    "3400930059692",
    "3400930059685",
    "3400930043578",
    "3400930043431",
    "3400930037614",
    "3400930037577",
    "3400930034217",
    "3400930034125",
    "3400930034040",
    "3400930031810",
    "3400930031742",
    "3400930037652",
    "3400930031568",
    "3400930031483",
    "3400930031421",
    "3400930026175",
    "3400930026151",
    "3400930026120",
    "3400930026106",
    "3400930026076",
    "3400949500109",
    "3400930023938",
    "3400930023921",
    "3400930023907",
    "3400930023891",
    "3400949500208",
    "3400930023884",
    "3400930023655",
    "3400930023648",
    "3400930023631",
    "3400930018972",
    "3400930017456",
    "3400930017357",
    "3400930017258",
    "3400927721908",
    "3400927721847",
    "3400941766930",
    "3400941766879",
    "3400941766701",
    "3400941766640",
    "3400941766589"
  )

  val atypiques = new PharmacologicalClassConfig(
    name = "Neuroleptiques_Neuroleptiques_atypiques",
    ATCCodes = List("N05A*"),
    ATCExceptions = List("N05AL06", "N05AN01", "N05AA", "N05AH02", "N05AH03", "N05AL05", "N05AX08", "N05AX12", "N05AA07")
  )

  val autres = new PharmacologicalClassConfig(
    name = "Neuroleptiques_Autres_neuroleptiques",
    ATCCodes = List("N05AA", "N05AH02", "N05AH03", "N05AL05", "N05AX08", "N05AX12")
  )

  val all = new PharmacologicalClassConfig(
    name = "Neuroleptiques_All_by_rule",
    ATCCodes = List("N05A*"),
    ATCExceptions = List("N05AL06", "N05AN01", "N05AA07")
  )

  val pharmacologicalClasses: List[PharmacologicalClassConfig] = List(atypiques, autres)
}
