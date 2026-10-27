-- import laser lua library functions
require("lalulib")

--Simple mode : guidanceLevel = 1
--Detail mode : guidanceLevel = 2
--Standard mode : guidanceLevel = 3

countryCode = "DNK"
unitArray = {"meter", "meter", "kilometer", "kilometer", "kilometer", "fod", "fod", "yard", "yard", "mil", "mil", "en kvart", "en halv", "trekvart"}

function getVersionInfo()
   local t =
   {
      ["country"] = "DNK",          -- ISO 3166-1 alpha-3 country code
      ["lang"] = "dan",             -- ISO 639-3  alpha-3 language code
      ["version"] = "1.5.1",       -- version
      ["date"] = "2024-07-02",      -- date of the last change
      ["lua"] = _VERSION,           -- lua version
      ["exe"] = os.date( "%H:%M:%S %Y-%m-%d"),  -- execution date
      ["utc"] = os.date( "!%H:%M:%S %Y-%m-%d"), -- UTC execution date
      ["project"] = "greatwall",    -- project title
      ["module"] = "guidance",      -- guidance
   }
   return dumpToString(t)
end

function spellWeatherType(weatherTypeNum)
   local s = ""
   if (weatherTypeNum == 1) then
      s = "Solrigt"                              --sunny
   elseif (weatherTypeNum == 2) then
      s = "Overskyet"                            --cloudy
   elseif (weatherTypeNum == 3) then
      s = "Gråvejr"                              --overcast
   elseif (weatherTypeNum == 4) then
      s = "Byge"                                 --shower
   elseif (weatherTypeNum == 5) then
      s = "Tordenbyge"                           --thundershower
   elseif (weatherTypeNum == 6) then
      s = "Tordenbyge med hagl"                  --thundershower with hail
   elseif (weatherTypeNum == 7) then
      s = "Slud"                                 --sleet
   elseif (weatherTypeNum == 8) then
      s = "Let regn"                             --light rain
   elseif (weatherTypeNum == 9) then
      s = "Moderat regn"                         --moderate rain
   elseif (weatherTypeNum == 10) then
      s = "Kraftig regn"                         --heavy rain
   elseif (weatherTypeNum == 11) then
      s = "Storm"                                --storm
   elseif (weatherTypeNum == 12) then
      s = "Kraftig storm"                        --heavy storm
   elseif (weatherTypeNum == 13) then
      s = "Stormvarsel"                          --severe storm
   elseif (weatherTypeNum == 14) then
      s = "Isslag"                               --ice rain
   elseif (weatherTypeNum == 15) then
      s = "Let til moderat regn"                 --light to moderate rain
   elseif (weatherTypeNum == 16) then
      s = "Moderat til kraftig regn"             --moderate to heavy storm
   elseif (weatherTypeNum == 17) then
      s = "Kraftig regn til uvejr"                --heavy rain to storm
   elseif (weatherTypeNum == 18) then
      s = "Kraftig regn til storm"                --storm to heavy storm
   elseif (weatherTypeNum == 19) then
      s = "Kraftig storm til stormvarsel"         --heavy to severe storm
   elseif (weatherTypeNum == 20) then
      s = "Let snefald"                           --snow flurry
   elseif (weatherTypeNum == 21) then
      s = "Let snebyge"                           --light snow
   elseif (weatherTypeNum == 22) then
      s = "Moderat sne"                           --moderate snow
   elseif (weatherTypeNum == 23) then
      s = "Kraftigt snefald"                      --heavy snow
   elseif (weatherTypeNum == 24) then
      s = "Snestorm"                              --snowstorm
   elseif (weatherTypeNum == 25) then
      s = "Let til moderat snefald"               --light to moderate snow
   elseif (weatherTypeNum == 26) then
      s = "Moderat til kraftigt snefald"          --moderate to heavy snow
   elseif (weatherTypeNum == 27) then
      s = "Kraftigt snefald til snestorm"         --heavy snow to snowstorm
   elseif (weatherTypeNum == 28) then
      s = "Tåget"                                 --foggy
   elseif (weatherTypeNum == 29) then
      s = "Tæt tåge"                              --dense fog
   elseif (weatherTypeNum == 30) then
      s = "Tyk, tæt tåge"                         --heavy dense fog
   elseif (weatherTypeNum == 31) then
      s = "Tyk tåge"                              --heavy fog
   elseif (weatherTypeNum == 32) then
      s = "Ekstra tyk, tæt tåge"                  --extra-heavy dense fog
   elseif (weatherTypeNum == 33) then
      s = "Støv"                                  --dust
   elseif (weatherTypeNum == 34) then
      s = "Sand"                                  --sand
   elseif (weatherTypeNum == 35) then
      s = "Sandstorm"                             --sandstorm
   elseif (weatherTypeNum == 36) then
      s = "Tågedis"                               --haze
   elseif (weatherTypeNum == 37) then
      s = "Moderat tågedis"                       --moderate haze
   elseif (weatherTypeNum == 38) then
      s = "Tæt tågedis"                           --severe haze
   elseif (weatherTypeNum == 39) then
      s = "Farlig tågedis"                        --hazardous haze
   else
      s = ""
   end
   return s
end

function spellRoughDirection(man)
   local s = ""
   if (man.DirOClock == 0) then
      s = "ligeud"                    --straight
   elseif (isInRanges(man.DirOClock, 1, 5)) then
      s = "til højre"                 --right
   elseif (isInRanges(man.DirOClock, 7, 11)) then
      s = "til venstre"               --left
   end
   return s
end

function spellFineDirection(man)

   -- if the route is in highway or toll road, then change slight left/right to left/right.
   if (man.FromProps.Area.motorway or man.FromProps.Area.toll) then
      if (man.DirOClock == 2) then
         man.DirOClock = 3
      elseif (man.DirOClock == 10) then
         man.DirOClock = 9
      end
   end

   local s = spellRoughDirection(man)
   if ((man.DirOClock == 1) or (man.DirOClock == 11)) then
      if (man.Action == "exit") then
         if (man.project == "zeekr") then
            s = conc("en smule", s)                   --slight
         else
            s = conc("let", s)                   --slight
         end
      end
   elseif ((man.DirOClock == 2) or (man.DirOClock == 10)) then
      if (man.project == "zeekr") then
         s = conc("en smule", s)                      --slight
      else
         s = conc("let", s)                      --slight
      end
   elseif (isInRanges(man.DirOClock, 4, 5, 7, 8)) then
      s = conc("skarpt", s)                   --sharp
   end
   return s
end

function getSoundFile(man, enableAutonomous)
   local s = ""
   return s
end

function testBeginDistance(isHighway, man)
   local ret = false
   local dist = getIntNumber(man.Distance)

   if (man.DistUnit == "kilometer") then
      if (isHighway) then
         ret = (dist >= 3000)
      else
         ret = (dist >= 2000)
      end
   elseif (man.DistUnit == "mile") then
      if (isHighway) then
         ret = (dist >= 2)
      else
         ret = (dist >= 1)
      end
   end
   return ret
end

function spellDistancePart(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (man.StageClass == "just_before") then
      if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
         s = ""
      else
         s = ""
      end
   elseif (man.Stage == 1) then
      if (guidanceLevel == 1) or (guidanceLevel == 3) then
         return s
      end
      if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
         if (man.Distance > 0) then
            if (man.project == "zeekr") then
               s = conc(s, "Om", spellDistanceAndUnit(man, countryCode, unitArray))                  --In about
            else
               s = conc(s, "Om ca.", spellDistanceAndUnit(man, countryCode, unitArray))                  --In about
            end
         end
      else
         s = "Om lidt "              --Soon
      end
   else
      if (man.Stage > 2) and ((guidanceLevel == 1) or (guidanceLevel == 3)) then
         return s
      end
      if (man.Distance > 0) then
         if (man.project == "zeekr") then
            s = conc(s, "Om", spellDistanceAndUnit(man, countryCode, unitArray))                  --In about
         else
            s = conc(s, "Om ca.", spellDistanceAndUnit(man, countryCode, unitArray))                  --In about
         end
      end
   end
   return s
end

function spellDirectionPart(man)
   local s = ""
   local isFromHighway = man.FromProps.Area.motorway
   if (isFromHighway == nil) then
      isFromHighway = false
   end

   -- roundabout exit voice
   if ((man.StageClass == "just_before") and (man.Action == "roundabout")) then
      s = conc(s, "Drej")              --turn
      if (man.HintProps.Hint.left_hand_traffic) then
         s = conc(s, "til venstre")           --left
      else
         s = conc(s, "til højre")             --right
      end
   elseif (man.HintProps.Hint.along_main_road) then
      s = "Bliv ved med at følge hovedvej"    --Keep following main road
   elseif (man.DirOClock == 0 and man.Action == "fork") then
      s = "hold til midten"                   --keep center
   elseif (man.DirOClock == 0) then
      if (man.Action == "exit" and isFromHighway) then
         s = ""
      else
         s = conc(s, "kør", spellRoughDirection(man))            --go
         if (man.Nth > 0) then
            s = conc(s, "", spellNthStreet(man.Nth, true))
         end
      end
   elseif (man.DirOClock == 6) then
      s = "lav en u-vending"                  --make a u-turn
   else
      if ((man.DirOClock == 1) or (man.DirOClock == 11)) then
         if (man.Action == "exit") then
            s = conc(s, "drej", spellFineDirection(man))     --turn
         else
            s = conc(s, "hold", spellFineDirection(man))     --keep
         end
      else
         s = conc(s, "drej", spellFineDirection(man))        --turn
            -- additional side street count information.
         if ((man.Nth > 0) and (((man.Stage == 1) and (man.StageClass ~= "just_before")) or (man.Stage == 2))) then
            s = conc(s, "", spellNthStreet(man.Nth, false))
         end
      end
   end
   return s
end

function spellDistanceAndDirectionPartForZeeker(man)
   local distStr = ""
   -- distance
   local guidanceLevel = man.GuidanceLevel
   local distType = 0 -- "":0, "her":1, "om x":2, "nu":3
   if (man.StageClass == "just_before") then
      if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
         distStr = ""
      else
         distStr = "her"                  --Here
         distType = 1
      end
   elseif (man.Stage == 1) then
      if (guidanceLevel == 1) or (guidanceLevel == 3) then
         distStr = ""
      else
         if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
            if (man.Distance > 0) then
               distStr = conc(s, "Om", spellDistanceAndUnit(man, countryCode, unitArray))                  --In about
               distType = 2
            end
         else
            distStr = "nu"              --Soon
            distType = 3
         end
      end
   else
      if (man.Stage > 2) and ((guidanceLevel == 1) or (guidanceLevel == 3)) then
         distStr = ""
      else
         if (man.Distance > 0) then
            distStr = conc(s, "Om", spellDistanceAndUnit(man, countryCode, unitArray))                  --In about
            distType = 2
         end
      end
   end

   -- direction
   -- roundabout exit voice
   local s = ""
   if ((man.StageClass == "just_before") and (man.Action == "roundabout")) then
      s = conc(s, "Drej")              --turn
      if (man.HintProps.Hint.left_hand_traffic) then
         s = conc(s, "til venstre")           --left
      else
         s = conc(s, "til højre")             --right
      end
   elseif (man.HintProps.Hint.along_main_road) then
      s = "Bliv ved med at følge hovedvej"    --Keep following main road
      if(distType == 3) then
         s = "Om lidt " .. s
      end
   elseif (man.DirOClock == 0 and man.Action == "fork") then
      s = "skal du fortsætte lige ud"                   --keep center
      if(distType == 3) then
         s = "Om lidt " .. s
      end
   elseif (man.DirOClock == 0) then
      if (man.Nth > 0 and distType == 3) then
         if (man.Nth == 1) then
            s = "venstre"            -- the left
         elseif (man.Nth == 2) then
            s = "midterste"             -- the center
         elseif (man.Nth == 3) then
            s = "højre"              -- the right
         end
         s = conc("Hold nu til", s)
      else
         s = conc(s, "skal du fortsætte lige ud")            --go
         if(distType == 3) then
            s = "Om lidt " .. s
         end
      end
   elseif (man.DirOClock == 6) then
      s = "lav en u-vending"                  --make a u-turn
      if(distType == 3) then
         s = "Om lidt " .. s
      end
   else
      if ((man.DirOClock == 1) or (man.DirOClock == 11)) then
         if (man.Action == "exit") then
            if (distType == 2) then
               s = conc(s, "skal du dreje", spellFineDirection(man))     --turn
            elseif (distType == 3) then
               s = conc(s, "Drej nu", spellFineDirection(man))     --turn
            else
               s = conc(s, "Drej", spellFineDirection(man))     --turn
            end
         else
            s = conc(s, "skal du holde", spellFineDirection(man))     --keep
         end
      else
         if (distType == 2) then
            s = conc(s, "skal du dreje", spellFineDirection(man))     --turn
         elseif (distType == 3) then
            s = conc(s, "Drej nu", spellFineDirection(man))     --turn
         else
            s = conc(s, "Drej", spellFineDirection(man))     --turn
         end
      end
   end

   if (distType == 2) then
      s = conc(distStr, s)
   elseif (distType == 1) then
      s = conc(s, distStr)
   end
   return s
end

function spellRoundaboutDirection(man)
   -- roundabout exit voice
   local s = "drej"                 --turn
   if (man.project == "zeekr") then
      s = s .. " nu"
   end

   if (man.HintProps.Hint.left_hand_traffic) then
      s = conc(s, "til venstre")    --left
   else
      s = conc(s, "til højre")      --right
   end
   return s
end

function getDirectionName(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (  ((guidanceLevel == 2 and man.Stage > 0 and man.StageClass ~= "just_before") or (guidanceLevel == 1 and man.Stage == 2) or (guidanceLevel == 3 and man.Stage == 2))
      and (isNotEmpty(man.TowardsProps.Name))
      and (man.TowardsProps.ObjectType ~= "road_number")
      and (man.TowardsProps.ObjectType ~= "road_name")) then
      s = man.TowardsProps.Name
   end
   return s
end

function getExitName(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (((guidanceLevel > 1 and man.Stage > 1) or (guidanceLevel == 1 and man.Stage == 2))
      and (isNotEmpty(man.HintProps.ExitName))) then
      s = man.HintProps.ExitName
   end
   return s
end

function spellNamesPart(man, useRoadNameOrNumber)
   local s = ""
   local isFromHighway = man.FromProps.Area.motorway
   if (isFromHighway == nil) then
      isFromHighway = false
   end
   local isToHighway = man.ToProps.Area.motorway
   if (isToHighway == nil) then
      isToHighway = false
   end
   local exitName = getExitName(man)
   local signPostName = getDirectionName(man)
   -- highway exit
   if (isFromHighway) then
      if (#exitName > 0) then
         if (man.project == "zeekr") then
            if (#signPostName > 0) then
               s = conc(s, "Tag afkørslen", exitName, "på", spellFineDirection(man), "mod", signPostName)
            else
               s = conc(s, "Tag afkørsel", exitName, "på", spellFineDirection(man))
            end
         else
            s = conc(s, " ved afkørsel", exitName)
         end
      end
      if (man.project ~= "zeekr" and #signPostName > 0) then
         s = conc(s, "mod", signPostName)
      end
      if (man.Action == "exit") then
         if (#exitName > 0) then
            s = s
         elseif (#signPostName > 0) then
            if (man.project == "zeekr") then
               s = conc("Tag afkørslen på", spellFineDirection(man), "mod", signPostName)
            else
               s = conc(s, " ,", " forlad motorvejen")
            end
         else
            if (man.project == "zeekr") then
               s = conc("Tag afkørslen på", spellFineDirection(man))
            else
               s = conc(s, " ,", " forlad motorvejen")
            end
         end
      end
      return s
   else
      local isRamp = man.ToProps.Area.ramp
      if (isRamp and #signPostName > 0) then
         s = conc(s, " mod", signPostName)            -- towards
      else
         if (useRoadNameOrNumber) then
            local roadName = getRoadNameOrNumber(man)
            if (#roadName > 0) then
               if (man.project == "zeekr") then
                  s = conc(s, "ind på", roadName)           -- onto
               else
                  s = conc(s, " på", roadName)           -- onto
               end
            end
         end
      end
   end
   return s
end

function spellIntersectionCount(man)
   -- not count intersection count in manual request.
   if (man.HintProps.Hint.manual_request) then
      return ""
   end

   local guidanceLevel = man.GuidanceLevel

   if (man.project == "zeekr" and man.Stage == 1 and guidanceLevel == 1) then
      return ""
   end

   local isHighway = man.FromProps.Area.motorway
   if (isHighway == nil) then
      isHighway = false
   end

   if (not man.IsIntersection) then
      return "";
   end
   if (man.ContainTrafficLight and man.TrafficLightIntersectionCount > 3 and man.StageClass ~= "just_before") then
      return ""
   end
   if ((not man.ContainTrafficLight) and man.IntersectionCount > 3 and man.StageClass ~= "just_before") then
      return "";
   end

   if (man.next ~= nil and man.Stage == 1 and man.StageClass ~= "just_before") then
      return ""
   end
   
   if (man.StageClass == "just_before"
      and (man.TrafficLightIntersectionCount == 0 and (man.IntersectionCount == 0 or man.IntersectionCount == 255))) then
      return ""
   end

   if (man.Stage == 4 and man.TrafficLightIntersectionCount == 0) then
      return "";
   end
   if (man.StageClass == "just_before" and man.IntersectionCount == 0) then
      return "";
   end
   local s = ""
   if (man.StageClass == "just_before" and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
      s = "ved"              --at the
      if (man.ContainTrafficLight) then
         if (man.TrafficLightIntersectionCount > 0) then
            if (man.project == "zeekr") then
               s = conc(s, "det", spellOrdinalNumber(man.TrafficLightIntersectionCount + 1), "trafiklys")
            else
               s = conc(s, " ", spellOrdinalNumber(man.TrafficLightIntersectionCount + 1))
            end
         end
         s = s .. " trafiklyset"       -- traffic light
      else
         s = conc(s, " ", spellOrdinalNumber(man.IntersectionCount + 1))
         s = s .. " krydset"           -- intersection
      end
   elseif (man.ContainTrafficLight and (not isHighway)) then
      if ((guidanceLevel == 1) and (man.StageClass == "just_before")) or ((guidanceLevel == 3) and (man.StageClass == "just_before")) or (guidanceLevel == 2) then
         s = "ved"                     --at the
         if (man.project == "zeekr") then
            if (man.TrafficLightIntersectionCount > 0) then
               s = conc(s, "det", spellOrdinalNumber(man.TrafficLightIntersectionCount + 1), "trafiklys")
            else
               s = s .. " trafiklyset"       -- traffic light
            end
         else
            if (man.TrafficLightIntersectionCount > 0) then
               s = conc(s, " ", spellOrdinalNumber(man.TrafficLightIntersectionCount + 1))
            end
            s = s .. " trafiklyset"       -- traffic light
         end
      end
   elseif (isHighway and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
      if (man.project == "zeekr") then
         s = "ved"
         local ordinalNum = spellOrdinalNumber(man.IntersectionCount + 1)
         if (#ordinalNum > 0) then
            s = conc(s, "det", ordinalNum, "kryds")
         else
            s = conc(s, "krydset")
         end
      else
         s = "i"                          --at the
         s = conc(s, " ", spellOrdinalNumber(man.IntersectionCount + 1))
         s = s .. " krydset"              -- intersection
      end
   end
   return s
end

function spellPrepositionAndGender(props)
   local s = ""
   if (props.Preposition == "into") or (props.Preposition == "onto") then
      s = "på"                  --onto
   end
   return s
end
function replaceAbbreviations(name)
   local s = name

   local phonemeStartStr = string.char(27) .. "\\lang="
   local isPhoneme = false
   if ((type(s) == "string") and (#s >= #phonemeStartStr)) then
      isPhoneme = (string.sub(s, 1, #phonemeStartStr) == phonemeStartStr)
   end
   if (not isPhoneme) then
      local replacementTbl = {
         {"%sN$", " North"},
         {"%sS$", " South"},
         {"%sE$", " East"},
         {"%sW$", " West"},

         {"%sNE$", " Northeast"},
         {"%sNW$", " Northwest"},
         {"%sSE$", " Southeast"},
         {"%sSW$", " Southwest"},

         {"^N%s", "North "},
         {"^S%s", "South "},
         {"^E%s", "East "},
         {"^W%s", "West "},

         {"^NE%s", "Northeast "},
         {"^NW%s", "Northwest "},
         {"^SE%s", "Southeast "},
         {"^SW%s", "Southwest "},

--         {"^I%-", "Interstate "},
         {"^US%-", "U S "},

         {"%sAve$", " Avenue"},
         {"%sBlvd$", " Boulevard"},
         {"%sBrg$", " Bridge"},
         {"%sCp$", " Camp"},
         {"%sCir$", " Circle"},
         {"%sCres$", " Crescent"},
         {"%sCt$", " Court"},
         {"%sDr$", " Drive"},
         {"%sHl$", " Hill"},
         {"%sLn$", " Lane"},
         {"%sPkwy$", " Parkway"},
         {"%sPl$", " Place"},
         {"%sPlz$", " Plaza"},
         {"%sRdg$", " Ridge"},
         {"%sRd$", " Road"},
         {"%sSq$", " Square"},
         {"%sSt$", " Street"},
         {"%sTer$", " Terrace"},
         {"%sTrce$", " Trace"},
         {"%sTrl$", " Trail"},

         {"%sAve%s", " Avenue "},
         {"%sBlvd%s", " Boulevard "},
         {"%sBrg%s", " Bridge "},
         {"%sCp%s", " Camp "},
         {"%sCir%s", " Circle "},
         {"%sCres%s", " Crescent "},
         {"%sCt%s", " Court "},
         {"%sDr%s", " Drive "},
         {"%sHl%s", " Hill "},
         {"%sLn%s", " Lane "},
         {"%sPkwy%s", " Parkway "},
         {"%sPl%s", " Place "},
         {"%sPlz%s", " Plaza "},
         {"%sRdg%s", " Ridge "},
         {"%sRd%s", " Road "},
         {"%sSq%s", " Square "},
         {"%sSt%s", " Street "},
         {"%sTer%s", " Terrace "},
         {"%sTrce%s", " Trace "},
         {"%sTrl%s", " Trail "},

         {"[%-%s]BL$", " Business Loop"},
         {"[%-%s]BS$", " Business Spur"},
         {"[%-%s]Bus.$", " Business Route"},
         {"[%-%s]Expy$", " Expressway"},
         {"[%-%s]Fwy$", " Freeway"},
         {"[%-%s]Hwy$", " Highway"},

         {"[%-%s]BL%s", " Business Loop "},
         {"[%-%s]BS%s", " Business Spur "},
         {"[%-%s]Bus.%s", " Business Route "},
         {"[%-%s]Expy%s", " Expressway "},
         {"[%-%s]Fwy%s", " Freeway "},
         {"[%-%s]Hwy%s", " Highway "},
      }

      for _, promptPart in ipairs(replacementTbl) do
         s = string.gsub(s, promptPart[1], promptPart[2])
      end
   end
   return s
end

function getRoadNameOrNumber(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (   ((guidanceLevel == 2 and man.Stage > 0 and man.StageClass ~= "just_before") or (guidanceLevel == 1 and man.Stage == 2) or (guidanceLevel == 3 and man.Stage == 2))
      and (isNotEmpty(man.ToProps.Name))
      and ((man.ToProps.ObjectType == "road_number")
         or (man.ToProps.ObjectType == "road_name"))) then

      if (man.ToProps.Name ~= man.FromProps.Name) then
         s = man.ToProps.Name
      end
   end
   return s
end

function spellOrdinalNumber(number)
   local s = ""
   if (number == 1) then
      s = "første"                  --first
   elseif (number == 2) then
      s = "anden"                   --second
   elseif (number == 3) then
      s = "tredje"                  --third
   elseif (number == 4) then
      s = "fjerde"                  --fourth
   elseif (number == 5) then
      s = "femte"                   --fifth
   elseif (number == 6) then
      s = "sjette"                  --sixth
   elseif (number == 7) then
      s = "syvende"                 --seventh
   elseif (number == 8) then
      s = "ottende"                 --eighth
   elseif (number == 9) then
      s = "niende"                  --ninth
   elseif (number == 10) then
      s = "tiende"                  --tenth
   elseif (number == 11) then
      s = "ellevte"                 --eleventh
   elseif (number == 12) then
      s = "tolvte"                  --twelfth
   else
      s = tostring(number) .. "."
   end
   return s
end

function spellTimeTTA(timeVal)
   local s = ""
   if (isNumber(timeVal)) then
      timeVal =  (timeVal >= 0) and (math.floor(timeVal + 0.5)) or (math.ceil(timeVal - 0.5))
      timeVal = timeVal + 30
      local hour = math.floor(timeVal / 3600)
      local minutes = math.floor((timeVal % 3600) / 60)
      if (hour > 1) then
         s = hour .. " timer"         -- hours
      elseif (hour == 1) then
         s = hour .. " time"          -- hour
      end

      if (minutes > 1) then
         s = s .. " " .. minutes .. " minutter"      -- minutes
      elseif(minutes == 1) then
         s = s .. " " .. minutes .. " minut"         -- minute
      end
   end
   if (#s > 0) then
      s = "Du kan få destinationen i " .. s          --You can get destination in 
   end
   return s
end

function spellTime(man, timeVal, isTwelveHoursClock, arrivalTimeMode)
   local s = ""
   if (arrivalTimeMode == "announce_arrival_time_mode_tta") then
      s = spellTimeTTA(timeVal);
      return s
   end
   if (isNumber(timeVal)) then
      timeVal =  (timeVal >= 0) and (math.floor(timeVal + 0.5)) or (math.ceil(timeVal - 0.5))
      timeVal = timeVal + 30
      local t = os.date("!*t", timeVal)
      if (t == nil) then
         --ignore date and daylight saving time and try again
         timeVal = timeVal % 86400 -- 60 * 60 * 24 secondsOfDay
         t = os.date("!*t", timeVal)
      end
      if (t ~= nil and t.hour ~= nil and t.min ~= nil) then
         local hourFormat = ((isTwelveHoursClock and "!%I") or "!%H")
         local hour = getIntNumber(os.date(hourFormat, timeVal))
         t.min = getIntNumber(t.min)

         if (t.min == 0) and (t.hour == 0) then
            s = "midnat"                    --midnight
         elseif (t.min == 0) and isInRanges(t.hour, 1, 11) then
            s = "Kl. " .. hour              -- o'clock
         elseif (t.min == 0) and (t.hour == 12) then
            s = "Kl. 12 middag"             --noon
         elseif (t.min == 0) and isInRanges(t.hour, 13, 23) then
            s = "Kl. " .. hour              -- o'clock
         elseif (isInRanges(t.min, 1, 9)) then
            s = hour .. " " .. t.min    -- o 
         else
            s = hour .. " " .. t.min
         end

         if (isTwelveHoursClock) then
            local zeroClock = false
            local twelveHoursClockMark = string.lower(os.date("!%p", timeVal))
            if (t.min == 0) and (t.hour == 0) then
               s = "midnat"                 --midnight
               zeroClock = true
            elseif (t.min == 0) and (t.hour == 12) then
               s = "Kl. 12 middag"          --noon
               zeroClock = true
            elseif (t.min == 0) then
               s = hour ..  ""
            elseif (isInRanges(t.min, 1, 9)) then
               s = hour .. " " .. t.min  -- o 
            else
               s = hour .. " " .. t.min
            end
            if (twelveHoursClockMark == "am") and (not zeroClock) then
               s = s .. " formiddag"                  -- a m
            elseif (twelveHoursClockMark == "pm") and (not zeroClock) then
               s = s .. " eftermiddag"                -- p m
            end
         end
      end
   end
   if (#s > 0) then
      if (man.project == "zeekr") then
         s = "Estimeret ankomsttidspunkt " .. s                --Estimated time of arrival,
      else
         s = "Anslået ankomsttid " .. s                --Estimated time of arrival,
      end
   end
   return s
end

function spellNthStreet(nth, isStraight)
   local s = ""
   if (nth == 1) then
      s = " venstre"            -- the left
   elseif (nth == 2) then
      s = " midterste"             -- the center
   elseif (nth == 3) then
      s = " højre"              -- the right
   end
   if (isStraight) then
      s = "til vej til" .. s            --"to" .. s .. " road"
   else
      s = "og vælg den" .. s .. " vej"  --"and take" .. s .. " road"
   end
   return s
end

function processIntersectionAction(man)
   if (man.project == "zeekr" and man.Action == "exit" and man.FromProps.Area.motorway) then
      local s1 = spellDistancePart(man)
      s1 = conc(s1, "", spellNamesPart(man, true))
      return s1
   end
   local s = spellDistancePart(man)
   if (man.project == "zeekr") then
      s = spellDistanceAndDirectionPartForZeeker(man)
   else
      s = conc(s, "", spellDirectionPart(man))
   end
   s = conc(s, "", spellIntersectionCount(man))
   s = conc(s, "", spellNamesPart(man, true))
   return s
end

function processIntersectionActionCombined(man)
   local s = ""
   s = spellDirectionPart(man)
   s = conc(s, spellNamesPart(man, false))

   if (#s > 0) then
      s = conc(" ", s)
      if (     (isAvailable(man.previous))
         and   (man.previous.Stage == 2)) then
         if (man.project ~= "zeekr") then
            s = conc(s, "snart")               --soon
         end
      elseif ((isAvailable(man.previous))
         and  (man.previous.Action == man.Action)
         and  ((not man.previous.HintProps.Hint.along_main_road) and man.HintProps.Hint.along_main_road)) then
         s = conc(s, "")
      elseif (     (isAvailable(man.previous))
         and   (man.previous.Action == man.Action)
         and   (man.previous.DirOClock == man.DirOClock)) then
         s = conc(s, "igen")                --again
      elseif ((isAvailable(man.previous))
         and  (man.previous.Action == man.Action)
         and  (spellDirectionPart(man.previous) == spellDirectionPart(man))) then
         s = conc(s, "igen")                --again
      end
   end
   return s
end

function processRoundaboutAction(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (man.project == "zeekr" and guidanceLevel == 1) then
      return s
   end
   if (man.Action == "roundabout_leave") then
      if (man.HintProps.Hint.first_announcement or
         (man.Stage == man.FirstStage and man.StageClass == man.FirstStageClass)) then
         if (man.project == "zeekr") then
            s = "Kør ud af rundkørslen ved den " .. spellOrdinalNumber(man.Nth) .. " afkørsel"      --"Take the " .. " exit and leave the roundabout"
         else
            s = "tag afkørsel " .. spellOrdinalNumber(man.Nth) .. " og kør ud af rundkørslen"      --"Take the " .. " exit and leave the roundabout"
         end
         return s
      end
   end
   if (man.Action == "roundabout" and ((man.Stage == 2 or man.Stage == 3) or (man.Stage == 1 and man.DerivedBegin))) then
      if (man.project == "zeekr") then
         if (man.Stage == 3) then
            s = "Rundkørsel forude"
         else
            s = "Kør ud af rundkørslen ved den " .. spellOrdinalNumber(man.Nth) .. " afkørsel"
         end
      else
         --s = "Om ca. " .. spellDistanceAndUnit(man, countryCode, unitArray) .. " kør ind i rundkørslen"    --"In about " .. " enter the roundabout"
         s = s .. ", og tag afkørsel " .. spellOrdinalNumber(man.Nth) .. " og kør ud af rundkørslen"      --", then take the " .. " exit and leave the roundabout"
      end
   elseif (man.StageClass == "roundabout_export") then
      if (guidanceLevel == 1) or (guidanceLevel == 3) then
         s = ""
      else
         s = "Kør ad " .. spellOrdinalNumber(man.CurrentRoundaboutExitNum) .. " afkørslen"  --"Go through the " .. " exit"
         if (man.CurRoundaboutExitPhaseCombinedWithNext) then
            s = concat(s, ", og tag afkørsel ", spellOrdinalNumber(man.CurrentRoundaboutExitNum + 1)) .. " og kør ud af rundkørslen" --", then take the ", .. " exit and leave the roundabout"
         end
         man._NoCombination = true
      end
   elseif (man.StageClass == "roundabout_exit") then
      if (man.project ~= "zeekr" or guidanceLevel > 1) then
         if (man.project == "zeekr") then
            s = spellRoundaboutDirection(man)          -- and leave the roundabout
         else
            s = spellRoundaboutDirection(man) .. " og kør ud af rundkørslen"          -- and leave the roundabout
         end
      end
   end
   return s
end

function processRoundaboutActionCombined(man)
   local s = spellDistancePart(man)
   s = conc(s, "kør ud af rundkørslen ved afkørsel", spellOrdinalNumber(man.Nth))       --"leave the roundabout at the", "exit"

   if ((#s > 0) and (isAvailable(man.previous)) and (man.previous.Stage == 2)) then
      s = conc(s, "snart")            --soon
   end
   return s
end

function processDestinationAction(man, onlyDirection)
   local s = ""
   local triggerVoice = false
   if (man.HintProps.Hint.manual_request) then
      triggerVoice = true
   elseif (man.project == "zeekr" and man.GuidanceLevel == 1) then
      triggerVoice = false
   elseif (man.Stage == 1) then
      triggerVoice = true
   elseif (man.DestinationAllPhases) then
      if (man.GuidanceLevel == 1) then
         triggerVoice = true
      elseif ((man.GuidanceLevel == 2 or man.GuidanceLevel == 3) and (man.Stage == 2 or man.Stage == 4)) then
         triggerVoice = true
      end
   end

   if (not triggerVoice) then
      return s
   end

   if (man.project == "zeekr") then
      if (man.FromProps.Area.crime and man.Stage == 1) then
         s = concat(s, " ", spellDestinationName(man))
      elseif (man.Stage == 1) then
         s = "Du er nået frem til"         --You have reached
         s = concat(s, " ", spellDestinationName(man))
      else
         s = spellDistancePart(man)
         if (onlyDirection) then
            s = ""
         end
         -- you will have reached
         if (man.FromProps.Area.ferry) then
            s = concat("Du når frem til", " ", spellDestinationName(man), s)
         else
            s = concat("Du når", " ", spellDestinationName(man), s)
         end
      end
   else
      if (man.FromProps.Area.crime and man.Stage == 1) then
         s = ""
      elseif (man.Stage == 1) then
         s = "Du er fremme ved "         --You have reached
      else
         s = spellDistancePart(man)
         if (onlyDirection) then
            s = ""
         end
         s = s .. " er du fremme ved "   -- you will have reached
      end

      s = concat(s, " ", spellDestinationName(man))
      if ((#s > 0) and (isAvailable(man.previous)) and (man.previous.Stage == 2)) then
            s = conc(s, "")
      end
   end

   return s
end

function spellDestinationName(man)

   local DestNumber = man.HintProps.CustomDestNumber
   if (DestNumber == nil or getIntNumber(DestNumber) > 100) then
      DestNumber = getIntNumber(man.TotalNumberOfDestinations) - getIntNumber(man.DestinationIndex)
   else
      DestNumber = getIntNumber(DestNumber)
   end
   local s = ""
   if (man.FromProps.Area.ferry) then
      if (man.project == "zeekr") then
         s = "færgen"               --the ferry terminal
      else
         s = "færgeterminalen"               --the ferry terminal
      end
   elseif (man.FromProps.Area.ipd) then
      s = conc(s, "ladestationer, oplad", spellNumber(man.Nth))           --the charging station, please charge to
      s = s .. "%"
   elseif (man.FromProps.Area.crime and man.Stage == 1) then
      s = "Nærmer sig Setel tankstation, har du brug for Setel service?" --Approaching Setel gas station, do you need Setel service?
   elseif (man.Nth == 0) then
      s = "destinationen"                 --the destination
      if (man.DirOClock == 9) then
         s = concat(s, ". ", "Den er til venstre")              --It is on the left
      elseif (man.DirOClock == 3) then
         s = concat(s, ". ", "Den er til højre")                --It is on the right
      end
   elseif (isInRanges(DestNumber, 1, 100)) then
      s = conc(s, "mellemdestination", spellNumber(DestNumber))   --the intermediate destination
      if (man.DirOClock == 9) then
         s = concat(s, ". ", "Den er til venstre")              --It is on the left.
      elseif (man.DirOClock == 3) then
         s = concat(s, ". ", "Den er til højre")                --It is on the right.
      end
   end

   if ((man.Nth == 0) and (man.Stage == 1) and (not isAvailable(man.previous)) ) then
      if (man.project ~= "zeekr") then
         s = concat(s, ". ", "Tak for turen")                      --Thank you for driving
      end
   end

   return s
end

function processEnterAction(man)
   man._NoCombination = true
   return ""
end

function processTollAction(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (man.project == "zeekr") then
      if (man.Stage == 2 or (man.Stage == 4 and guidanceLevel == 3)) then
         return s
      end
   end
   if (guidanceLevel == 1) then
      if (man.Stage == 2) then
         s = "Snart nået betalingsanlæg"              --Soon reach toll gate
      end
   elseif (guidanceLevel == 3) then
      if (man.Stage == 2) then
         s = "Snart nået betalingsanlæg"
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 3) ) then
         if (man.project == "zeekr") then
            s = "Betalingsanlæg forude"
         else
            s = "Betalingsanlæg forud"
         end
      end
   elseif (guidanceLevel == 2) then
      if ((man.Stage == 1) and (man.StageClass ~= "just_before")) then
         s = "Snart nået betalingsanlæg."             --Soon reach toll gate
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 2) ) then
         s = "Betalingsanlæg forud"                   --Toll gate ahead
      end
   end
   return s
end

function processTunnelAction(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (man.project == "zeekr" and man.Stage == 2 and guidanceLevel == 3) then
      return s
   end
   if (guidanceLevel == 1) then
      s = ""
   elseif (guidanceLevel == 3) then
      if (man.Stage == 2) then
         s = "Snart Tunnel forud"                --Soon tunnel ahead
      end
   elseif (guidanceLevel == 2) then
      if ((man.Stage == 1) and (man.StageClass ~= "just_before")) then
         s = "Snart Tunnel forud"                --Soon tunnel ahead
      elseif (man.project == "zeekr" and man.Stage == 2 and guidanceLevel == 2) then
         s = "Snart Tunnel forud"                --Soon tunnel ahead
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 2) ) then
         s = "Tunnel forud"                      --tunnel ahead
      end
   end
   return s
end

function processEnterActionWarning(man, AnnouncementIsNotEmpty)
   local s = "Følg de faktiske færdselsregler"   --Please follow actual traffic regulations
   if (man.project == "zeekr") then
      s = "Følg de faktiske trafikbestemmelser"
   end
   return s
end

function processEnterActionCombined(man)
   local s = ""
   if (man.ToProps.Area.tunnel and (not man.FromProps.Area.tunnel)) then
      s = processTunnelAction(man)
   end
   if (man.ToProps.Area.toll_gate and (not man.FromProps.Area.toll_gate)) then
      s = processTollAction(man)
   end
   if ((#s > 0) and (isAvailable(man.previous)) and (man.previous.Stage == 2)) then
         s = conc(s, "snart")                   --soon
   end
   return s
end

function processMotorwayEnterAction(man)
   local s = spellDistancePart(man)
   if (man.project == "zeekr") then
      s = spellDistanceAndDirectionPartForZeeker(man)
   else
      s = conc(s, "", spellDirectionPart(man))
   end

   if (man.project ~= "zeekr") then
      s = conc(s, "", ", du har nået motorvejstilkørslen")         --, you have reached the highway entrance
   end
   s = conc(s, "", spellMotorWayEnterNamesPart(man, true))
   return s
end


function spellMotorWayEnterNamesPart(man, useRoadNameOrNumber)
   local s = ""
   local signPostName = getDirectionName(man)
   -- motorway entry
   if (man.HintProps.Hint.motorway_entry) then
       if (#signPostName > 0) then
         s = conc(s, ", mod", signPostName)                --, towards
      end
         return s
   end
   return s
end

function processLaneMergeAction(man)
   local s = ""
   local firstStage = getIntNumber(man.FirstStage)
   if ((firstStage < 1) or (firstStage > 2)) then
      firstStage = 2
   end
   if (man.Stage == firstStage) then
      s = "Vær forsigtig med at samle trafik"               --Be careful of merging traffic
      if ((man.DirOClock ~= 0) and (man.DirOClock ~= 6)) then
         s = conc(s, "fra", spellRoughDirection(man))       --from the
      end
   end
   return s
end


function processFollowRoadAction(man)
   local s = ""
   if (man.Distance > 0) then
      s = conc(s, "Inden for ca.", spellDistanceAndUnit(man, countryCode, unitArray)) --Within about
   end
   s = conc(s, "følg aktuel rute")                            --Please follow current road
   return s
end

function processDPTrafficLightAction(man)
   local s = "trafiklyset krydset forud, Kør ligeud"        --Traffic light intersection ahead, please go straight
   if (man.project == "zeekr") then
      s = "Lyskryds forude, gå lige ud"
   end
   if (man.Stage == 1 and man.TrafficLightIntersectionCount > 0) then
      s = "Kør ligeud ad " .. spellOrdinalNumber(man.TrafficLightIntersectionCount) .. " trafiklyset"      --"Go straight through the " .. " traffic light"
   end
   return s
end

function processDPRecomLaneAction(man)
   local s = ""
   if (man.IsContiLaneVoice) then
      if (man.BestRecomNum > 0) then
         if (not man.IsManeuver) then
            local dir = spellDirection(man.RecomLaneDir)
            if (man.project ~= "zeekr") then
               if (dir ~= "") then
                  s = conc(dir, ".")
               end
            end
         end
         if (man.project == "zeekr") then
            s = conc(s, "Anbefaling: Tag")     --Recommend taking the
         else
            s = conc(s, "Anbefales at vælge")     --Recommend taking the
         end
      else
         if (man.project == "zeekr") then
            s = conc(s, "Tag")                   --Please take the
         else
            s = conc(s, "Vælg")                   --Please take the
         end
      end
      local pos = ""
      if ((man.IsInc2nd) and (man.IsInc3rd)) then
         if (man.project == "zeekr") then
            s = conc(s, "den anden eller tredje bane fra")    --second and third lanes
            pos = spellPosition(false, man.RecomLanePos, man)
         else
            s = conc(s, "anden og tredje vognbane")    --second and third lanes
            pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos, man)
         end
         s = conc(s, pos)
      elseif (man.IsInc2nd) then
         s = conc(s, "vognbanen anden")             --second lane
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos, man)
         s = conc(s, pos)
      elseif (man.IsInc3rd) then
         s = conc(s, "vognbanen tredje")            --third lane
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos, man)
         s = conc(s, pos)
      elseif (((man.BestRecomNum == 1) or (man.RecomNum == 1)) and (man.RecomLanePos == 1)) then
         if (man.project == "zeekr") then
            s = conc(s, "banen længst til venstre")         --leftmost lane
         else
            s = conc(s, "vognbanen yderst til venstre")         --leftmost lane
         end
      elseif (((man.BestRecomNum == 1) or (man.RecomNum == 1)) and (man.RecomLanePos == 2)) then
         if (man.project == "zeekr") then
            s = conc(s, "banen længst til højre")           --rightmost lane
         else
            s = conc(s, "vognbanen yderst til højre")           --rightmost lane
         end
      else
         if (man.project == "zeekr") then
            if (man.BestRecomNum > 1) then
               local bestNumber = convToString(man.BestRecomNum)
               s = conc(s, "en af de", bestNumber, "baner")             --lanes
            elseif (man.RecomNum > 1) then
               local number = convToString(man.RecomNum)
               s = conc(s, "en af de", number, "baner")                 --lanes
            else
               s = conc(s, "banen")                         --lane
            end
         else
            if (man.BestRecomNum > 1) then
               local bestNumber = convToString(man.BestRecomNum)
               s = conc(s, bestNumber, "vognbaner")             --lanes
            elseif (man.RecomNum > 1) then
               local number = convToString(man.RecomNum)
               s = conc(s, number, "vognbaner")                 --lanes
            else
               s = conc(s, "vognbanen")                         --lane
            end
         end
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos, man)
         s = conc(s, pos)
      end
   else
      if (not man.IsManeuver) then
         local dir = spellDirection(man.RecomLaneDir)
         if (man.project ~= "zeekr") then
            if (dir ~= "") then
               s = conc(dir, ".")
            end
         end
      end
      if (man.project == "zeekr") then
         local pos = spellPosition(man.isContiLaneVoice, man.RecomLanePos, man)
         if (man.RecomNum > 1) then
            local number = convToString(man.RecomNum)
            s = conc(s, "Tag en af de", number, "baner", pos)
         else
            s = conc(s, "Tag banen", pos)
         end
      else
         s = conc(s, "Vælg")              --Please take the
         local pos = spellPosition(man.isContiLaneVoice, man.RecomLanePos, man)
         s = conc(s, pos)
         if (man.RecomNum > 1) then
            local number = convToString(man.RecomNum)
            s = conc(s, number, "vognbaner")       --lanes
         else
            s = conc(s, "vognbanen")               --lane
         end
      end
   end
   return s
end

function spellDirection(dir)
   local s = ""
   if (dir == 1) then
      s = "drej skarpt til venstre"       --Turn sharp left
   elseif (dir == 2) then
      s = "drej til venstre"              --Turn left
   elseif (dir == 3) then
      s = "drej let til venstre"          --Turn slight left
   elseif (dir == 4) then
      s = "drej skarpt til højre"         --Turn sharp right
   elseif (dir == 5) then
      s = "drej til højre"                --Turn right
   elseif (dir == 6) then
      s = "drej let til højre"            --Turn slight right
   elseif (dir == 7) then
      s = "kør direkte på"                --Go straight on
   elseif (dir == 8) then
      s = "lav en u-vending"              --Make a u-turn
   end
   return s
end

function spellPosition(isContiLaneVoice, position, man)
   local s = ""
   if (isContiLaneVoice) then
      if (position == 1) then
         s = "til venstre"               --on the left
      elseif (position == 2) then
         s = "til højre"                 --on the right
      elseif (position == 3) then
         if (man.project == "zeekr") then
            s = "i midten"
         else
            s = "til midten"                --on the center
         end
      end
   else
      if (position == 1) then
         s = "venstre"                   --left
      elseif (position == 2) then
         s = "højre"                     --right
      elseif (position == 3) then
         s = "midten"                    --center
      end
   end
   return s
end

function processDPSAPAAction(man)
   local s = ""
   local distM = man.DistM
   local nextDistM = man.NextDistM
   local distKm = getIntNumber(distM / 1000)
   local nextDistKm = getIntNumber(nextDistM / 1000)
   if (man.project == "zeekr") then
      if ((distKm >= 1) and testDistance3(distM, nil) and (spellDistanceAndUnit(man, countryCode, unitArray) ~= "")) then
         s = conc("Du kommer til en rasteplads om cirka", spellDistanceAndUnit(man, countryCode, unitArray))                 --In
         if ((nextDistKm >= 1) and testDistance4(nextDistM, nil) and (spellDistanceAndUnit(man, countryCode, unitArray, true) ~= "")) then
            s = conc(s, ". Det næste er om cirka", spellDistanceAndUnit(man, countryCode, unitArray, true))   --. The next one is in
         end
      end
   else
      if ((distKm >= 1) and testDistance3(distM, nil) and (spellDistanceAndUnit(man, countryCode, unitArray) ~= "")) then
         s = conc("er om ca.", spellDistanceAndUnit(man, countryCode, unitArray))                 --In
      end
      s = conc(getAreaType(man.AreaType), s)              --ahead
      if ((nextDistKm >= 1) and testDistance4(nextDistM, nil) and (spellDistanceAndUnit(man, countryCode, unitArray, true) ~= "")) then
            s = conc(s, ". Næste er om ca.", spellDistanceAndUnit(man, countryCode, unitArray, true))   --. The next one is in
      end
   end
   return s
end

function getAreaType(type)
   local s = ""
   if (type == 1000) then
     s = "Serviceområde"                     --service area
   elseif (type == 1001) then
     s = "parkeringsområde"                  --parking area
   end
   return s
end

function processWNHighAccidentAreaAction(man)
   local s = "Område med højt ulykkestal forud. Kør forsigtigt"                --High accident area ahead. Please drive carefully
   if (man.project == "zeekr") then
      s = "Område med mange ulykker forude. Kør forsigtigt"
   end
   return s
end

function processWNSchoolZoneAction(man)
   local s = "Skolezone forud. Kør forsigtigt"          --School zone ahead. Please drive carefully
   if (man.project == "zeekr") then
      s = "Skolezone forude. Kør forsigtigt"
   end
   return s
end

function processWNRailwayAction(man)
   local s = "Jernbane forud. Kør forsigtigt"           --Railway ahead. Please drive carefully
   if (man.project == "zeekr") then
      s = "Jernbaneoverførsel forude. Kør forsigtigt"
   end
   return s
end

function processWNDestParkingAction(man)
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel == 2) then
      s = "Du har fået anbefalet destination parkeringsplads, klik på Kør"   --The destination parking lot has been recommended for you, click Go
      if (man.project == "zeekr") then
         s = "En parkeringsplads fundet tæt på destinationen, tryk på OK"
      end
   end
   return s
end

function processWNDestChargingStationAction(man)
   local s = "Anbefalinger til ladestationer i nærheden af destinationen"       --Recommendations of charging stations near the destination
   if (man.project == "zeekr") then
      s = "Ladestationer fundet tæt på destinationen"
   end
   return s
end

function processWNTunnelAction()
   local s = "Tunnel forud"              --Tunnel ahead
   return s
end

function processWNGpsAction()
   local s = "Det aktuelle GPS-signal er svagt, og positionsopdateringen kommer muligvis ikke rettidig"             --The current GPS signal is weak, and the position update may not be timely
   return s
end

function processWNLaneMergeAction(man)
   local s = ""
   if (man.project == "zeekr") then
      if (man.LaneMergeType == 1) then
         s = "venstre"                --Left side
      elseif (man.LaneMergeType == 2) then
         s = "højre"                  --Right side
      end
      s = conc("Sammenfletning af trafik fra", s, "forude. Kør forsigtigt")          --merger ahead. Please drive carefully
   else
      if (man.LaneMergeType == 1) then
         s = "til venstre"                --Left side
      elseif (man.LaneMergeType == 2) then
         s = "til højre"                  --Right side
      end
      s = conc("Sammenfletning", s, "forude. Kør forsigtigt")          --merger ahead. Please drive carefully
   end
   return s
end

function processWNTrafficCameraAction(man)
   local s = ""
   if (man.project == "zeekr") then
      if (man.CameraType == 1) then
         s = "Fartkamera forude"                         --A speed camera is
      elseif (man.CameraType == 7) then
         s = "Kameraer for fart og rødt lys forude"              --Speed and red light cameras are
      elseif (man.CameraType == 3) then
         s = "Kamera for rødt lys forude"                      --A red light camera is
      elseif (man.CameraType == 11) then
         s = "Kontrolzone for gennemsnitsfart forude"     --You are driving into a speed measurement section
      elseif (man.CameraType == 12) then
         s = "Afslutning på kontrolzone for gennemsnitsfart"      --You are leaving the speed measurement section
      else
         return s
      end

      local currentDist = man.Distance
      if (testDistance(currentDist, nil) and (spellDistanceAndUnit(man, countryCode, unitArray) ~= "")) then
         s = conc(s, "om", spellDistanceAndUnit(man, countryCode, unitArray))           --ahead
      end
      return s
   end

   if (man.CameraType == 1) then
      s = "Et fartkamera"                         --A speed camera is
   elseif (man.CameraType == 7) then
      s = "Fart- og rødtlyskameraer"              --Speed and red light cameras are
   elseif (man.CameraType == 3) then
      s = "Et rødtlyskamera"                      --A red light camera is
   elseif (man.CameraType == 11) then
      s = "kører du ind i et område med hastighedsmåling"     --You are driving into a speed measurement section
   elseif (man.CameraType == 12) then
      s = "forlader du i et område med hastighedsmåling"      --You are leaving the speed measurement section
   else
      return s
   end
   local currentDis = man.Distance
   if (testDistance(currentDis, nil) and (spellDistanceAndUnit(man, countryCode, unitArray) ~= "")) then
      if (man.CameraType == 11 or man.CameraType == 12) then
         s = conc("Om", spellDistanceAndUnit(man, countryCode, unitArray), s)           --ahead
      else
         s = conc(s, spellDistanceAndUnit(man, countryCode, unitArray), "forude")           --ahead
      end
   else
      s = conc(s, "forud")  --ahead
   end
   local speedLimit = man.SpeedLimit
   local unit = "km/t"      --km/h
   if (speedLimit > 0) then
      if (man.DistUnit == "mile" or man.DistUnit == "quartermile" or man.DistUnit == "yard" or man.DistUnit == "foot") then
         speedLimit = getIntNumber(speedLimit / 1.609344 / 5 + 0.5) * 5;
         unit = "mph"
      end
   end
   local limitedSpeed = convToString(speedLimit)
   if ((man.CameraType == 1) and (limitedSpeed ~= "0")) then
      s = conc(s, ". Hastighedsgrænsen er", limitedSpeed, unit)                   --. The limit is
   elseif ((man.CameraType == 7) and (limitedSpeed ~= "0")) then
      s = conc(s, ". Hastighedsgrænsen er", limitedSpeed, unit)                   --. The limit is
   elseif (man.CameraType == 3) then
      s = conc(s, ". Kør forsigtigt")                                             --. Please drive carefully
   elseif ((man.CameraType == 11) and (limitedSpeed ~= "0")) then
      s = conc(s, ". Hastighedsgrænsen er", limitedSpeed, unit)                   --. The limit is
   end
   return s
end

function processWNCurveAction(man)
   local s = ""
   if (man.project == "zeekr") then
      if (man.IsContiCurve) then
         s = "Kontinuerlig kurve forude. Kør forsigtigt"    --Continuous curve ahead. Please drive carefully
      else
         s = "Kurve forude. Kør forsigtigt"                 --Curve ahead. Please drive carefully
      end
   else
      if (man.IsContiCurve) then
         s = "Kontinuerlig kurve forud. Kør forsigtigt"    --Continuous curve ahead. Please drive carefully
      else
         s = "Kurve forud. Kør forsigtigt"                 --Curve ahead. Please drive carefully
      end
   end
   return s
end

function processWNTrafficAction(man)
   local s = ""
   local content = getTrafficContent(man.TrafficKind)
   if (content ~= "") then
      local distM = man.DistM
      local distKm = getIntNumber(distM / 1000)
      if ((distKm >= 1) and testDistance2(distM, nil) and (spellDistanceAndUnit(man, countryCode, unitArray) ~= "")) then
         if (man.project == "zeekr") then
            s = conc("Om", spellDistanceAndUnit(man, countryCode, unitArray), ",")    --In about
         else
            s = conc("Om ca.", spellDistanceAndUnit(man, countryCode, unitArray), ",")    --In about
         end
      end
      s = conc(s, "Der er", content)              --there is a
   end
   return s
end

function processWNOverspeedAction(man)
   local s = ""
   local speedLimit = man.SpeedLimit
   local carSpeed = man.CarSpeed
   local speedLimitStr = ""
   local carSpeedStr = ""
   local unit = "km/t"       --km/h
   if (man.DistUnit == "mile") then
      speedLimit = getIntNumber(speedLimit / 1.609344 / 5 + 0.5) * 5;
      unit = "mph"
   end
   speedLimitStr = convToString(speedLimit)
   carSpeedStr = convToString(carSpeed)
   s = "Hastighedsgrænse er " .. speedLimitStr .. " " .. unit .. ". Du overskrider hastighedsgrænsen"          --"Speed limit is " .. speedLimitStr .. ". You are exceeding the speed limit"
   return s
end

function processWNBorderAction(man)
   local s = ""
   if (man.BorderName ~= "") then
      s = conc("Kører ind i", man.BorderName)             --Entering
   end
   return s
end

function processWNInappLaneAction(man)
   local s = ""
   if (man.InappLaneReason == 0) then
      return s
   end
   if (man.InappLaneReason == 1) then
      -- no entry
      if (spellTimeForInappLane(man.FromHour, man.FromMinutes) ~= "" and spellTimeForInappLane(man.ToHour, man.ToMinutes) ~= "") then
         s = "Den nuværende bane er forbudt fra " .. spellTimeForInappLane(man.FromHour, man.FromMinutes) .. " til " .. spellTimeForInappLane(man.ToHour, man.ToMinutes)      --"The current lane is forbidden from " .. " to "
      end
   else
      -- other reasons
      if (spellInappLaneReason(man.InappLaneReason) ~= "") then
         s = "Den nuværende bane er dedikeret til " .. spellInappLaneReason(man.InappLaneReason)   --The current lane is dedicated to 
      end
   end
   return s
end

function processWNNarrowLaneAction(man)
   local s = ""
   if (man.NarrowLaneVoiceType == 1) then
      if (man.NarrowLaneDir == 1) then
         s = "Indsnævre fremad venstre bane"               --Forward left lane narrows
      elseif (man.NarrowLaneDir == 2) then
         s = "Indsnævre fremadgående højre bane"           --Forward right lane narrows
      elseif (man.NarrowLaneDir == 3) then
         s = "Fremad begge baner smalle"                   --Forward both lanes narrow
      else
         return s
      end
      s = s .. ". Kør forsigtigt"                          --. Please drive carefully
   elseif (man.NarrowLaneVoiceType == 2) then
      if (man.NarrowLaneDir == 1) then
         s = "venstre"                   --left side
      elseif (man.NarrowLaneDir == 2) then
         s = "højre"                     --right side
      else
         return s
      end
      s = "Vejen forude bliver smallere. Skift bane til " .. s    --The road ahead becomes narrower. Please change lanes to the 
   end
   return s
end

function processEVLowBattery(man)
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel > 1) then
      s = "Lavt batteri, oplad"             --Low battery, please charge
   end
   return s
end

function processEVFewChargingStations(man)
   local s = ""
   if (man.GuidanceLevel > 1) then
      s = "Der er få ladestationer. Oplad rettidigt"          --There are few charging stations. Please charge timely
      if (man.project == "zeekr") then
         s = "Der er nogle få ladestationer. Oplad rettidigt"
      end
   end
   return s
end

function processSRPSuccess()
   local s = "Planlægning af intelligent opladningsrute afsluttet"
   return s
end

function processSRPFail()
   local s = "Battery level not sufficient to arrive at the destination. No charging station found along the route, please charge timely"
   return s
end

function processBeepDRGPopup(man)
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel > 1) then
      local minutes = convToString(man.Minutes)
      if (man.project == "zeekr") then
         s = conc("Ny rute fundet, du sparer", minutes, "minutter")   --A new route found, .. minutes will be saved
      else
         s = conc("Ny rute fundet,", minutes, "minutter kan spares")   --A new route found, .. minutes will be saved
      end
   end
   return s
end

function spellTimeForInappLane(hour, min)
   local s = ""
   if (isNumber(hour) and isNumber(min)) then
      if (min == 0) and (hour == 0) then
         s = "midnat"                --midnight
      elseif (min == 0) and isInRanges(hour, 1, 11) then
         s = "Kl. " .. hour          -- o'clock
      elseif (min == 0) and (hour == 12) then
         s = "Kl. 12 middag"         --noon
      elseif (min == 0) and isInRanges(hour, 13, 23) then
         s = "Kl. " .. hour          -- o'clock
      elseif (isInRanges(min, 1, 9)) then
         s = hour .. " o " .. min .. ""    -- o 
      else
         s = hour .. " " .. min .. ""
      end
   end
   return s
end

function spellInappLaneReason(reason)
   local s = ""
   if (reason == 2) then
      s = "HOV"                   --HOV
   elseif (reason == 3) then
      s = "taxis"                 --taxis
   elseif (reason == 4) then
      s = "buses"                 --buses
   elseif (reason == 5) then
      s = "ambulances"            --ambulances
   end
   return s
end

function getTrafficContent(kind)
   local s = ""
   if (kind == 0) then
      s = "trafikprop"                   --congestion
   elseif (kind == 1) then
      s = "Forhindring"                  --obstacle
   elseif (kind == 2) then
      s = "Ulykke"                       --accident
   elseif (kind == 3) then
      s = "Vejarbejde"                   --road work
   elseif (kind == 4) then
      s = "smalle vognbaner"             --narrow lanes
   elseif (kind == 5) then
      s = "glat vej"                     --slippery road
   elseif (kind == 6) then
      s = "akvaplaning"                  --aquaplaning
   elseif (kind == 7) then
      s = "brand"                        --fire
   elseif (kind == 8) then
      s = "Nedsunket vej"                --road subsidence
   elseif (kind == 9) then
      s = "havareret køretøj"            --disabled vehicle
   elseif (kind == 10) then
      s = "vej lukket"                   --road closed
   elseif (kind == 11) then
      s = "ekstreme vejrforhold"         --extreme weather conditions
   elseif (kind == 12) then
      s = "uagtsomme personer"           --reckless persons
   elseif (kind == 13) then
      s = "advarselssystem for overhøjde er udløst" --over height warning system triggered
   elseif (kind == 14) then
      s = "færdselsregler er ændret"                --traffic regulations changed
   elseif (kind == 15) then
      s = "større hændelse"                         --major event
   elseif (kind == 16) then
      s = "service er ikke anvendelig"              --service not usable
   elseif (kind == 17) then
      s = "langsomt køretøj"                        --slow moving vehicle
   elseif (kind == 18) then
      s = "farlig slutning på kø"                   --dangerous end of queue
   elseif (kind == 19) then
      s = "risiko for brand"                        --risk of fire
   elseif (kind == 20) then
      s = "Ekstra sikkerhedsforanstaltninger"       --extra security measures
   elseif (kind == 21) then
      s = "politikontrolsted"                       --police checkpoint
   elseif (kind == 22) then
      s = "defekt vejsideudstyr"                    --malfunctioning roadside equipment
   end
   return s
end

function testDistance(distance, maxDistanceInM)
   local ret = false
   if (maxDistanceInM == nil) then
      maxDistanceInM = 999
   end
   local distInM = getIntNumber(distance)
   ret = isInRanges(distInM, 1, maxDistanceInM)
   return ret
end

function testDistance2(distance, maxDistanceInM)
   local ret = false
   if (maxDistanceInM == nil) then
      maxDistanceInM = 20000
   end
   local distInM = getIntNumber(distance)
   ret = isInRanges(distInM, 1, maxDistanceInM)
   return ret
end

function testDistance3(distance, maxDistanceInM)
   local ret = false
   if (maxDistanceInM == nil) then
      maxDistanceInM = 5000
   end
   local distInM = getIntNumber(distance)
   ret = isInRanges(distInM, 1, maxDistanceInM)
   return ret
end

function testDistance4(distance, maxDistanceInM)
   local ret = false
   if (maxDistanceInM == nil) then
      maxDistanceInM = 999999
   end
   local distInM = getIntNumber(distance)
   ret = isInRanges(distInM, 1, maxDistanceInM)
   return ret
end

function processMainAction(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel

   -- check warning voice
   if (man.Action == "dp_traffic_light" or
      man.Action == "dp_recom_lane" or
      man.Action == "dp_sapa" or
      man.Action == "wn_high_accident_area" or
      man.Action == "wn_school_zone" or
      man.Action == "wn_railway" or
      man.Action == "wn_dest_parking" or
      man.Action == "wn_dest_charging_station" or
      man.Action == "wn_tunnel" or
      man.Action == "wn_gps" or
      man.Action == "wn_lane_merge" or
      man.Action == "wn_traffic_camera" or
      man.Action == "wn_curve" or
      man.Action == "wn_traffic" or
      man.Action == "wn_overspeed" or
      man.Action == "wn_border" or
      man.Action == "wn_inapp_lane" or
      man.Action == "wn_narrow_lane" or
      man.Action == "ev_low_battery" or
      man.Action == "ev_few_charging_stations" or
      man.Action == "srp_success" or
      man.Action == "srp_fail" or
      man.Action == "beep_drg_popup") then
      s = processWarningAction(man)
      return s
   end

   if (man.Action == "guidance_starts" or
      man.Action == "enter_better_route" or
      man.Action == "blocked_road" or
      man.Action == "time_regulation_road" or
      man.Action == "no_guidance_active" or
      man.Action == "drive_to_route" or
      man.Action == "ti_route_changed" or
      man.Action == "version") then
      s = processCommonAction(man)
      return s
   end

   if (man.HintProps.Hint.first_announcement) then
      s = conc(s, processFirstAnnouncement(man))
      man._NoCombination = true
      return s
   end

   s = conc(s, processNormalAction(man))
   return s
end

function processCommonAction(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   if (man.Action == "guidance_starts") then
      if (man.project == "zeekr") then
         if (guidanceLevel > 1) then
            s = "Følg de faktiske trafikbestemmelser"
         end
      else
         s = "Følg de faktiske færdselsregler"             --Please follow actual traffic regulations
      end
   elseif (man.Action == "enter_better_route") then
      if (guidanceLevel > 1) then
         s = "Har indtastet den bedre rute"                --Have entered the better route
      else
         s = ""
      end
   elseif (man.Action == "blocked_road") then
      if (guidanceLevel > 1) then
         s = "Vej blokeret foran, guide med ny rute"       --Road blocked ahead, guide with new route
      else
         s = ""
      end
   elseif (man.Action == "time_regulation_road") then
      if (guidanceLevel > 1) then
         s = "Tidsregulering fremad, guide med ny rute"    --Time regulation ahead, guide with new route
      else
         s = ""
      end
   elseif (man.Action == "no_guidance_active") then
      s = "Angiv venligst rute"                         --Please set route
   elseif (man.Action == "drive_to_route") then
      if (man.project == "zeekr") then
         s = "Fortsæt ad den markerede rute"  --Please follow the route. Guidance to destination is then starting
      else
         s = "Følg vejen. Vejledning til destination starter"  --Please follow the route. Guidance to destination is then starting
      end
   elseif (man.Action == "ti_route_changed") then
      s = "Optimal rute opdateres"                      --Optimal route is updated
   elseif (man.Action == "version") then  --special version info
      s = getVersionInfo(man)
   end
   if (#s > 0) then
      man._NoCombination = true
   end
   return s
end

function processFirstAnnouncement(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel
   local DestNumber = man.HintProps.CustomDestNumber
   if (DestNumber == nil or getIntNumber(DestNumber) > 100) then
      DestNumber = getIntNumber(man.TotalNumberOfDestinations) - getIntNumber(man.DestinationIndex)
   else
      DestNumber = getIntNumber(DestNumber)
   end
   if (guidanceLevel == 2) then
      if (man.project == "zeekr") then
         if ((getIntNumber(man.TotalNumberOfDestinations) == 1) or (getIntNumber(man.DestinationIndex) == 0)) then
            s = conc(s, " Navigation er startet")          -- Guidance to destination is starting
         elseif (isInRanges(DestNumber, 1, 5)) then
            local destNumberStr = spellOrdinalNumber(DestNumber) .. "-waypointet"
            s = conc(s, " Navigation til", destNumberStr, "er startet")         --" Guidance to intermediate destination", "is starting"
         end
      else
         if ((getIntNumber(man.TotalNumberOfDestinations) == 1) or (getIntNumber(man.DestinationIndex) == 0)) then
            s = conc(s, " Vejledning til destination starter")          -- Guidance to destination is starting
         elseif (isInRanges(DestNumber, 1, 5)) then
            s = conc(s, " Vejledning til mellemdestination", spellNumber(DestNumber), "starter")         --" Guidance to intermediate destination", "is starting"
         end
      end
      local aTime = spellTime(man, man.ArrivalTime, man.HintProps.Hint.clock_format_12_hour, man.HintProps.ArrivalTimeMode)
      if (#aTime > 0) then
         s = conc(s .. ".", aTime)
      end

      s = conc(s, ".", processWeatherTypesString(man))
   end

   if (man.Action == "roundabout_leave" and
      man.Stage == man.FirstStage and
      man.StageClass == man.FirstStageClass) then
      s = conc(s, processRoundaboutAction(man))
   end
   return s
end

function processWeatherTypesString(man)
   local s = ""
   if (getTableValueCount(man.WeatherTypes) > 0) then
      local weatherTypeCount = 1;
      if (man.project == "zeekr") then
         s = conc(s , "Vejret ved destination,")   -- The weather at the destination is
      else
         s = conc(s , " Vejret på destinationen er ")   -- The weather at the destination is
      end
      for key, weatherType in pairsSortedByKeys(man.WeatherTypes) do
         if (weatherType ~= nil) then
            if (weatherTypeCount > 1) then
               s = conc(s, " til ")                  -- to
            end
            local weatherTypeStr = spellWeatherType(weatherType.weather_type)
            if (weatherTypeStr == "") then
               return ""
            else
               s = conc(s , weatherTypeStr)
            end
            weatherTypeCount  = weatherTypeCount + 1;
         end
      end
   end
   return s
end

function processNormalAction(man)
   local s = ""
   local guidanceLevel = man.GuidanceLevel

   if (man.HintProps.Hint.manual_request) then
      man._NoCombination = true
      guidanceLevel = 2 -- priority boost
   end

   if (guidanceLevel == 1) or (guidanceLevel == 3) then
      s = processActionSimpleMode(man)
   elseif (guidanceLevel == 2) then
      s = processActionDetailMode(man)
   end

   if (man.HintProps.Hint.manual_request) then
      if ((s == nil or #s == 0) and (man.FromProps.Area.off_road ~= true)) then -- when no other announcement can be generated
         s = "Følg vejen"             --Please follow the road
      end

      local aTime = spellTime(man, man.ArrivalTime, man.HintProps.Hint.clock_format_12_hour, man.HintProps.ArrivalTimeMode)
      if ((#aTime > 0) and (#s > 0)) then
         s = conc(s .. ".", aTime)
      elseif ((#aTime > 0) and (#s == 0)) then
         s = conc("", aTime)
      end
   end
   return s
end

function processActionSimpleMode(man)
   local s = ""
   if (man.Action == "roundabout" or
      man.Action == "roundabout_leave" or
      man.Action == "destination" or
      man.HintProps.Hint.along_main_road or
      (man.ToProps.Area.toll_gate and (not man.FromProps.Area.toll_gate)) or
      (man.ToProps.Area.tunnel and (not man.FromProps.Area.tunnel))) then
      s = processSpecialManeuverSimple(man)
      return s
   else
      s = processNormalManeuverSimple(man)
   end
   if (s ~= "") then
      if (man.Stage == 1 and man.StageClass == "just_before") then
         man._NoCombination = true
      end
   end
   return s
end

function processSpecialManeuverSimple(man)
   local s = ""
   if (man.Action == "enter") then
      if (man.Stage <= 2) then
         if (man.Stage == 2 or
            (man.FirstStage == man.Stage and man.StageClass == man.FirstStageClass)) then
            if (man.ToProps.Area.toll_gate and (not man.FromProps.Area.toll_gate)) then
               s = processTollAction(man)
            elseif (man.ToProps.Area.tunnel and (not man.FromProps.Area.tunnel)) then
               s = processTunnelAction(man)
            end
         end
      end
   elseif (man.Action == "roundabout") then
      if (man.Stage <= 2) then
         if (man.Stage == 2 or
            (man.Stage == 1 and man.StageClass == "roundabout_exit") or
            (man.Stage == 1 and man.DerivedBegin) or
            (man.Stage == man.FirstStage and man.StageClass == man.FirstStageClass)) then
            s = processRoundaboutAction(man)
         end
      end
   elseif (man.Action == "roundabout_leave") then
      if (man.Stage == 2 or
         (man.Stage == 1 and man.StageClass == "roundabout_exit") or
         (man.Stage == man.FirstStage and man.StageClass == man.FirstStageClass)) then
         s = processRoundaboutAction(man)
      end
   elseif (man.HintProps.Hint.along_main_road) then
      if (man.Stage <= 2) then
         if (man.Stage == 2 or
            (man.FirstStage == man.Stage and man.StageClass == man.FirstStageClass)) then
            s = processAlongMainRoadSimpleVoice(man)
         end
      end
   elseif (man.Action == "destination") then
      s = processDestinationAction(man, false)
   end
   return s
end

function processAlongMainRoadSimpleVoice(man)
   local s = ""
   -- along main road send action phase sentence at approach phase when the mode is simple
   s = "Om lidt Bliv ved med at følge hovedvej"                  --Soon Keep following main road
   return s
end

function processNormalManeuverSimple(man)
   local s = ""

   local isMotorway = false
   if (man.FromProps.Area.motorway) then
      isMotorway = true
   end

   -- normal maneuvers begin and prpare phase doesn't speak at simple mode
   if (man.Stage <= 2) then
      if (man.Stage == 2 or
         (man.Stage == 1 and man.StageClass == "just_before") or
         (man.FirstStage == man.Stage and man.StageClass == man.FirstStageClass)) then
         if ( (not man.HintProps.Hint.first_maneuver) and
             (not man.HintProps.Hint.first_announcement) and
             (testBeginDistance(isMotorway, man))) then
               s = processFollowRoadAction(man)
         elseif (man.HintProps.Hint.motorway_entry) then
            if (man.project == "zeekr" and (man.Stage == 2 or man.StageClass == "just_before") and man.GuidanceLevel == 1) then
               s = ""
            else
               s = processMotorwayEnterAction(man)
            end
         elseif (man.Action == "intersection") or (man.Action == "fork") or (man.Action == "roundabout_enter") then
            if (man.Stage == 1 and man.StageClass ~= "just_before") then
               s = ""
            elseif (man.project == "zeekr" and (man.Stage == 2 or man.StageClass == "just_before") and man.GuidanceLevel == 1) then
               s = ""
            else
               s = processIntersectionAction(man)
            end
         elseif (man.Action == "exit") then
            if (man.Stage == 1 and man.StageClass ~= "just_before") then
               s = ""
            elseif (man.project == "zeekr" and (man.Stage == 2 or man.StageClass == "just_before") and man.GuidanceLevel == 1) then
               s = ""
            else
               s = processIntersectionAction(man)
            end
         elseif (man.StageClass ~= "just_before") then
            if (man.Action == "follow_road") then
               s = processFollowRoadAction(man)
            elseif (man.Action == "lane_merge") then
               s = processLaneMergeAction(man)
            end
         end
      end
   end
   return s
end

function processActionDetailMode(man)
   local s = ""

   local isMotorway = false
   if (man.FromProps.Area.motorway) then
      isMotorway = true
   end

   if ( (not man.HintProps.Hint.first_maneuver) and
       (not man.HintProps.Hint.first_announcement) and
       (testBeginDistance(isMotorway, man))) then
      s = processFollowRoadAction(man)
   elseif (man.ToProps.Area.toll_gate and (not man.FromProps.Area.toll_gate)) then
      s = processTollAction(man)
   elseif (man.ToProps.Area.tunnel and (not man.FromProps.Area.tunnel)) then
      s = processTunnelAction(man)
   elseif (man.HintProps.Hint.along_main_road) then
      if ((man.Stage == man.FirstStage and man.StageClass == man.FirstStageClass) or (man.Stage == 1 and man.StageClass ~= "just_before")) then
         s = processIntersectionAction(man)
      else
         man._NoCombination = true
      end
   elseif (man.Action == "roundabout" or man.Action == "roundabout_leave") then
      s = processRoundaboutAction(man)
   elseif (man.HintProps.Hint.motorway_entry) then
      s = processMotorwayEnterAction(man)
   elseif (man.Action == "intersection") or (man.Action == "fork") or (man.Action == "roundabout_enter") then
      s = processIntersectionAction(man)
   elseif (man.Action == "exit") then
      s = processIntersectionAction(man)
   elseif (man.StageClass ~= "just_before") then
      if (man.Action == "destination") then
         s = processDestinationAction(man, false)
      elseif (man.Action == "follow_road") then
         s = processFollowRoadAction(man)
      elseif (man.Action == "lane_merge") then
         s = processLaneMergeAction(man)
      end
   end
   return s
end

function processWarningAction(man)
   local s = ""
   if (man.Action == "dp_traffic_light") then
      s = processDPTrafficLightAction(man)
   elseif (man.Action == "dp_recom_lane") then
      s = processDPRecomLaneAction(man)
   elseif (man.Action == "dp_sapa") then
      s = processDPSAPAAction(man)
   elseif (man.Action == "wn_high_accident_area") then
      s = processWNHighAccidentAreaAction(man)
   elseif (man.Action == "wn_school_zone") then
      s = processWNSchoolZoneAction(man)
   elseif (man.Action == "wn_railway") then
      s = processWNRailwayAction(man)
   elseif (man.Action == "wn_dest_parking") then
      s = processWNDestParkingAction(man)
   elseif (man.Action == "wn_dest_charging_station") then
      s = processWNDestChargingStationAction(man)
   elseif (man.Action == "wn_tunnel") then
      s = processWNTunnelAction()
   elseif (man.Action == "wn_gps") then
      s = processWNGpsAction()
   elseif (man.Action == "wn_lane_merge") then
      s = processWNLaneMergeAction(man)
   elseif (man.Action == "wn_traffic_camera") then
      s = processWNTrafficCameraAction(man)
   elseif (man.Action == "wn_curve") then
      s = processWNCurveAction(man)
   elseif (man.Action == "wn_traffic") then
      s = processWNTrafficAction(man)
   elseif (man.Action == "wn_overspeed") then
      s = processWNOverspeedAction(man)
   elseif (man.Action == "wn_border") then
      s = processWNBorderAction(man)
   elseif (man.Action == "wn_inapp_lane") then
      s = processWNInappLaneAction(man)
   elseif (man.Action == "wn_narrow_lane") then
      s = processWNNarrowLaneAction(man)
   elseif (man.Action == "ev_low_battery") then
      s = processEVLowBattery(man)
   elseif (man.Action == "ev_few_charging_stations") then
      s = processEVFewChargingStations(man)
   elseif (man.Action == "srp_success") then
      s = processSRPSuccess()
   elseif (man.Action == "srp_fail") then
      s = processSRPFail()
   elseif (man.Action == "beep_drg_popup") then
      s = processBeepDRGPopup(man)
   end
   return s
end

function processCombinedAction(man, mainString)
   local s = ""
   if (man.project == "zeekr" and man.Stage == 1 and guidanceLevel == 1) then
      return s
   end
   --other actions, no linguistic combination
   if (man.Action == "no_guidance_active") then
      s = processMainAction(man)
      s = concat(mainString, ". ", s)
      return s
   end

   if (#mainString > 0) then
      if (   (isAvailable(man.previous))
         and (man.previous.Stage < 3) and (man.previous.StageClass ~= "just_before")) then
         if (man.Action == "intersection" or man.Action == "fork") then
            s = processIntersectionActionCombined(man)
         elseif (man.Action == "exit") then
            s = processIntersectionActionCombined(man)
         elseif (man.Action == "enter") then
            s = processEnterActionCombined(man)
         elseif (man.Action == "destination") then
            s = processDestinationAction(man, true)
         else
            s = ""
         end
      end
      if (man.project == "zeekr") then
         if string.find(mainString, "nu til") then
            if string.find(s, "drej til ") then
               s = string.gsub(s, "drej til ", "og derefter til ", 1)
               s = mainString .. " " .. s
            else
               s = concat(mainString, " og derefter ", s)    -- and then
            end
         elseif string.find(s,"drej til ") then
            s = string.gsub(s, "drej til ", ". Drej derefter til ", 1)
            s = mainString .. s
         else
            s = concat(mainString, " og derefter ", s)    -- and then
         end
      else
         s = concat(mainString, " og derefter ", s)    -- and then
      end
   end
   return s
end

function split(str)
   local t={}
   local startPoint = 1
   local endPoint
   repeat
      local vStart, vEnd = string.find(str, "[^%s]+", startPoint)
      startPoint = vStart
      endPoint = vEnd
      if (startPoint) then
         table.insert(t, string.sub(str, startPoint, endPoint))
         startPoint = endPoint + 1
      end
   until (not startPoint)
   return t
end

function integrateAudioSeq(filename, completeWavPath)
   local s = ""
   if ((type(filename) == "string") and (#filename > 0)) then
      s = string.char(27) .. "\\audio=\"" .. completeWavPath .. filename .. "\"\\"
   end
   return s
end

local function getWaveFileTokens()
   local tokens =
   {
      --other
      ["Pingpong"] = {"PINPON_5"},
      ["Pingpong_AD"] = {"NTS_AD_kokuchi_"},
      ["Pong"] = {"PON_2"},
      ["Ponpon"] = {"PONPO-N"},
      --other
   }
   return tokens
end

local function getSoundFileTokens()
   local tokens =
   {
      -- sounds
      ["Pingpong"] = {"PINPON_5"},
      ["Pingpong_AD"] = {"PINPON_5"},
      ["Pong"] = {"PON_2"},
      ["Ponpon"] = {"PONPO-N"},
   }
   return tokens
end

function replaceTokens(promptTable, wavPath)
   local s = ""
   local dirSeparator = package.config:sub(1,1)
   local tokens = getWaveFileTokens()
   wavPath = convToString(wavPath)

   if (isNotEmpty(wavPath)) then
      if (string.sub(wavPath, -1) ~= dirSeparator) then
         wavPath = wavPath .. dirSeparator
      end
      local genericWavPath = wavPath .. "generic" .. dirSeparator

      local markTbl =
      {
         ["."] = ".",
         [","] = ",",
      }

      for _, promptPart in ipairs(promptTable) do
         local appendix = ""
         repeat
            local endChar = string.sub(promptPart, -1)
            local translation = markTbl[endChar]
            if (translation ~= nil) then
               appendix = translation .. appendix
               promptPart = string.sub(promptPart, 1, #promptPart - 1)
            end
         until (translation == nil)

         if (#promptPart > 0) then
            local replacementTbl = tokens[promptPart]
            if (replacementTbl ~= nil) then
               promptPart = ""
               for _, replacement in ipairs(replacementTbl) do
                  local rep = convToString(replacement)
                  if (#rep > 0) then
                     promptPart = promptPart .. integrateAudioSeq(rep .. ".wav", genericWavPath)
                  end
               end
               appendix = ""  -- no appendix, because in some cases point is pronounced as dot
            end
         end
         s = conc(s, promptPart .. appendix)
      end
   end
   return s
end

function main(...)
   local s = ""
   local isVersionRequest = false
   local previousMan
   local paramCount = select('#', ...)
   local mainStage = 0
   local mainVoiceDataPath = ""
   local mainUsePhoneme = true
   local isManualRequest = false

   logPrint(dump(...))
   for j = 1, paramCount do
      local man = select(j, ...)

      if (type(man) == "table") then

         if ((j + 1) <= paramCount) then
            man.next = select(j + 1, ...)
         end
         man.DirOClock = getIntNumber(man.DirOClock) % 12
         man.Stage = getIntNumber(man.Stage)
         local prototypeManTbl = {
            ["FromProps"] = {["Area"] = {}},
            ["ToProps"] = {["Area"] = {}},
            ["TowardsProps"] = {["Area"] = {}},
            ["HintProps"] = {["Hint"] = {}}}
         setPrototypeTable(man, prototypeManTbl)

         if (j == 1) then
            mainVoiceDataPath = convToString(man.HintProps.VoiceDataPath)
            mainUsePhoneme = (man.HintProps.Hint.use_phoneme == true)
            s = processMainAction(man)
            mainStage = getIntNumber(man.Stage)
            isVersionRequest = (man.Action == "version")
            isManualRequest = man.HintProps.Hint.manual_request
         else
            man.previous = previousMan
            if ((#s == 0) and (mainStage == 2)) then
               man.Stage = mainStage
               man.Distance = man.Distance + previousMan.Distance
               s = processMainAction(man)
            else
               s = processCombinedAction(man, convToString(s))
            end
            man.previous = nil
         end
         man.next = nil
         if (man._NoCombination) then
            break
         end
         previousMan = man
      end
   end
   if ((#s > 0) and (not isVersionRequest)) then
      s = trim(s)     
      s = s .. "."
   end
   if ((replaceWithFilenames) and (not isVersionRequest) and (#mainVoiceDataPath > 0) and (mainUsePhoneme)) then
      local v = s
      local t = split(s)
      s = replaceTokens(t, mainVoiceDataPath)
      if (s ~= v) then
         logPrint("IntermediateResult: " .. v)
      end
      if (isManualRequest and #s > 0) then
         s = string.char(27) .. "\\mrk=REPEAT\\" .. s -- Marking for the BL to identify a repeated announcement
      end
   end
   return s
end

function dump(...)
   local s = ""
   for j = 1, select('#', ...) do
      local object = select(j, ...)
      s =  conc(s, "Parameter ", j, ": ", dumpToString(object))
   end
   return s
end

function logPrint(message, isWarning)
   if (  ((not _G.logOutputDisabled) or (isWarning))
      and (type(message) == "string")
      and (#message > 0)) then

      if (_G.logOutput) then
         _G.logOutput(message)
      else
         print(message)
      end
   end
end

function changeWaveFileReplacement( enable)
   replaceWithFilenames = (enable == true)
end

local startlua = {_NAME="start"}
startlua.main = main
startlua.dump = dump
startlua.spellSpecNr = spellNumber
startlua.spellNumber = spellNumber
startlua.spellTime = spellTime
startlua.spellDistanceAndUnit = spellDistanceAndUnit
startlua.getWaveFileTokens = getSoundFileTokens
startlua.getSoundFileTokens = getSoundFileTokens
startlua.changeWaveFileReplacement = changeWaveFileReplacement

return startlua