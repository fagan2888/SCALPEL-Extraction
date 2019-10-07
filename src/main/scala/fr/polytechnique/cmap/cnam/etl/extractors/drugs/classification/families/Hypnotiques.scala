// License: BSD 3 clause

package fr.polytechnique.cmap.cnam.etl.extractors.drugs.classification.families

import fr.polytechnique.cmap.cnam.etl.extractors.drugs.classification.{DrugClassConfig, PharmacologicalClassConfig}

object Hypnotiques extends DrugClassConfig {

  val name = "Hypnotiques"

  val cip13Codes: Set[String] = Set(
    "3400932572601",
    "3400932531158",
    "3400932531097",
    "3400932530908",
    "3400932400065",
    "3400932399833",
    "3400932399772",
    "3400931113034",
    "3400931112891",
    "3400931112723",
    "3400931112662",
    "3400931112433",
    "3400930059531",
    "3400935887559",
    "3400933147044",
    "3400930959695",
    "3400930959466",
    "3400937701358",
    "3400937701297",
    "3400936298859",
    "3400936200685",
    "3400934758218",
    "3400934758157",
    "3400932430161",
    "3400932429912",
    "3400931720324",
    "3400931077091",
    "3400931076902",
    "3400935887849",
    "3400935746931",
    "3400934174049",
    "3400934173967",
    "3400934043826",
    "3400933651565",
    "3400933651336",
    "3400931533597",
    "3400931489962",
    "3400926924041",
    "3400926923969",
    "3400938554465",
    "3400938025859",
    "3400937920360",
    "3400936743489",
    "3400936583320",
    "3400936263901",
    "3400936141742",
    "3400936106734",
    "3400936039346",
    "3400935443151",
    "3400935248091",
    "3400934884337",
    "3400934884276",
    "3400934884108",
    "3400934884047",
    "3400934883965",
    "3400934883675",
    "3400937979153",
    "3400934883507",
    "3400934856181",
    "3400934856013",
    "3400934772344",
    "3400934772283",
    "3400933826284",
    "3400933825973",
    "3400931742845",
    "3400931534778",
    "3400930008102",
    "3400927713972",
    "3400932520732",
    "3400931813736",
    "3400931723806",
    "3400939755120",
    "3400935339843",
    "3400935339492",
    "3400933116866",
    "3400932610723",
    "3400931836629",
    "3400949695256",
    "3400938838565",
    "3400938836035",
    "3400937800853",
    "3400936742130",
    "3400936742079",
    "3400936582897",
    "3400936429116",
    "3400936429055",
    "3400936210042",
    "3400936209961",
    "3400936209732",
    "3400936209671",
    "3400936209442",
    "3400936190887",
    "3400936181724",
    "3400935830197",
    "3400935829948",
    "3400935829597",
    "3400935817822",
    "3400936209503",
    "3400935817761",
    "3400935817303",
    "3400935389121",
    "3400935388988",
    "3400935130945",
    "3400935130884",
    "3400934891489",
    "3400934891311",
    "3400936584150",
    "3400937800914",
    "3400934891199",
    "3400934891021",
    "3400934857591",
    "3400934857423",
    "3400949695317",
    "3400934837661",
    "3400934837432",
    "3400934832529",
    "3400934832468",
    "3400934563218",
    "3400934563157",
    "3400932644551",
    "3400932644490",
    "3400930062166",
    "3400932729630",
    "3400932729579",
    "3400932403028",
    "3400926977849",
    "3400932606702",
    "3400932606641",
    "3400932322312",
    "3400930081433",
    "3400930081372",
    "3400930081204",
    "3400930081143",
    "3400930006467",
    "3400930006344",
    "3400930006238",
    "3400927899195",
    "3400927898075",
    "3400927896873",
    "3400927895814",
    "3400927894633",
    "3400933722517",
    "3400938609448",
    "3400938609387",
    "3400938401080",
    "3400934582196",
    "3400934582028",
    "3400933353797",
    "3400930658277",
    "3400930369494",
    "3400930369265",
    "3400930368954",
    "3400930658567",
    "3400937920131",
    "3400937665537",
    "3400936946293",
    "3400934948992",
    "3400934802591",
    "3400932899524",
    "3400932285778",
    "3400935539694",
    "3400930681749",
    "3400934846137",
    "3400934846076",
    "3400933995409",
    "3400932752980",
    "3400932179060",
    "3400933445102",
    "3400932815197",
    "3400932422296",
    "3400935447005",
    "3400934966743",
    "3400933147624",
    "3400932986842",
    "3400927567131",
    "3400927567070",
    "3400936085879",
    "3400936085701",
    "3400932839032",
    "3400932753062",
    "3400932487813",
    "3400935284921",
    "3400935255594",
    "3400935255426",
    "3400935247490",
    "3400935247261",
    "3400934887468",
    "3400934887000",
    "3400935285003",
    "3400934784460",
    "3400934748561",
    "3400934554971",
    "3400934554803",
    "3400932849895",
    "3400932742066",
    "3400930018613",
    "3400930003671",
    "3400938979992",
    "3400938653090",
    "3400938525489",
    "3400938020946",
    "3400936772694",
    "3400936772526",
    "3400936583788",
    "3400936145184",
    "3400936145016",
    "3400936052581",
    "3400936052413",
    "3400935878106",
    "3400935877734",
    "3400936583849",
    "3400935843036",
    "3400935842954",
    "3400935589262",
    "3400935589033",
    "3400938122763",
    "3400935482471",
    "3400935482303",
    "3400935445162",
    "3400935344236",
    "3400938980073",
    "3400935298539",
    "3400935298478",
    "3400935298010",
    "3400935297877",
    "3400935286352",
    "3400935286291",
    "3400935285751",
    "3400935285690",
    "3400939390024",
    "3400936749344",
    "3400936749283",
    "3400936713093",
    "3400936712904",
    "3400936712843",
    "3400936712782",
    "3400936456839",
    "3400936455719",
    "3400936455429",
    "3400936454477",
    "3400936454187",
    "3400936451346",
    "3400936451285",
    "3400936450974",
    "3400936450516",
    "3400936450455",
    "3400936455078",
    "3400936388901",
    "3400936388840",
    "3400936219618",
    "3400936215993",
    "3400936617599",
    "3400936215535",
    "3400936191259",
    "3400936191198",
    "3400936103023",
    "3400936099777",
    "3400936099609",
    "3400936099548",
    "3400936099487",
    "3400939390192",
    "3400936026049",
    "3400936025967",
    "3400934797309",
    "3400934658570",
    "3400934045318",
    "3400933903619",
    "3400933354800",
    "3400932961078",
    "3400932228768",
    "3400930718681",
    "3400930421574",
    "3400930166451",
    "3400930166390",
    "3400930166222",
    "3400930165911",
    "3400931415862",
    "3400931507284"
  )

  val benzodiazepineAnxiolytiques = new PharmacologicalClassConfig(
    name = "Hypnotiques_Benzodiazepines_anxiolytiques",
    ATCCodes = List("N05BA*")
  )

  val autresAnxiolytiques = new PharmacologicalClassConfig(
    name = "Hypnotiques_Autres_anxiolytiques",
    ATCCodes = List("N05BB*", "N05BC*", "N05BE*", "N05BX*"),
    ATCExceptions = List("N05BC51")
  )

  val benzodiazepineHypnotiques = new PharmacologicalClassConfig(
    name = "Hypnotiques_Benzodiazepines_hypnotiques",
    ATCCodes = List("N05CD*"),
    ATCExceptions = List("N05CD08")
  )

  val autresHypnotiques = new PharmacologicalClassConfig(
    name = "Hypnotiques_Autres_hypnotiques",
    ATCCodes = List("N05CF*", "N05BC51", "N05CM11", "N05CM16", "N05CX")
  )

  val all = new PharmacologicalClassConfig(
    name = "Hypnotiques_All_by_rule",
    ATCCodes = List("N05B*", "N05CD*", "N05CF*", "N05CM11", "N05CM16", "N05CX"),
    ATCExceptions = List("N05CD08")
  )

  val pharmacologicalClasses = List(
    benzodiazepineAnxiolytiques,
    autresAnxiolytiques,
    benzodiazepineHypnotiques,
    autresHypnotiques
  )

}
