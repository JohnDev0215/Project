-- import laser lua library functions
require("lalulib")

--Simple mode : guidanceLevel = 1
--Detail mode : guidanceLevel = 2
--Standard mode : guidanceLevel = 3

countryCode = "ITA"
unitArray = {"metri", "metri", "chilometro", "chilometri", "kilometri", "piedi", "piedi", "iarde", "iarde", "miglio", "miglia", "un quarto di", "mezzo", "tre quarti di"}

function getVersionInfo()
   local t =
   {
      ["country"] = "ITA",          -- ISO 3166-1 alpha-3 country code
      ["lang"] = "ita",             -- ISO 639-3  alpha-3 language code
      ["version"] = "1.18.1",       -- version
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
      s = "soleggiato"                        -- eng: sunny
   elseif (weatherTypeNum == 2) then
      s = "nuvoloso"                          -- eng: cloudy
   elseif (weatherTypeNum == 3) then
      s = "coperto"                           -- eng: overcast
   elseif (weatherTypeNum == 4) then
      s = "Pioggia"                            -- eng: shower
   elseif (weatherTypeNum == 5) then
      s = "Temporale"                -- eng: thundershower
   elseif (weatherTypeNum == 6) then
      s = "Temporale con grandine"            -- eng: thundershower with hail
   elseif (weatherTypeNum == 7) then
      s = "Pioggia di neve"                         -- eng: sleet
   elseif (weatherTypeNum == 8) then
      s = "pioggia leggera"                   -- eng: light rain
   elseif (weatherTypeNum == 9) then
      s = "pioggia moderata"                  -- eng: moderate rain
   elseif (weatherTypeNum == 10) then
      s = "Pioggia forte"                     -- eng: heavy rain
   elseif (weatherTypeNum == 11) then
      s = "Tempesta"                          -- eng: storm
   elseif (weatherTypeNum == 12) then
      s = "Tempesta forte"                    -- eng: heavy storm
   elseif (weatherTypeNum == 13) then
      s = "Tempesta violenta"                    -- eng: severe storm
   elseif (weatherTypeNum == 14) then
      s = "Pioggia congelante"               -- eng: ice rain
   elseif (weatherTypeNum == 15) then
      s = "pioggia da leggera a moderata"     -- eng: light to moderate rain
   elseif (weatherTypeNum == 16) then
      s = "tempesta da moderata a forte"      -- eng: moderate to heavy storm
   elseif (weatherTypeNum == 17) then
      s = "Pioggia forte a tempesta"       -- eng: heavy rain to storm
   elseif (weatherTypeNum == 18) then
      s = "Tempesta a forte tempesta"        -- eng: storm to heavy storm
   elseif (weatherTypeNum == 19) then
      s = "Tempesta da forte a violenta"         -- eng: heavy to severe storm
   elseif (weatherTypeNum == 20) then
      s = "Nevicate"                   -- eng: snow flurry
   elseif (weatherTypeNum == 21) then
      s = "neve leggera"                      -- eng: light snow
   elseif (weatherTypeNum == 22) then
      s = "neve moderata"                     -- eng: moderate snow
   elseif (weatherTypeNum == 23) then
      s = "Neve forte"                    -- eng: heavy snow
   elseif (weatherTypeNum == 24) then
      s = "Tempesta di neve"                  -- eng: snowstorm
   elseif (weatherTypeNum == 25) then
      s = "Neve da leggera a moderata"        -- eng: light to moderate snow
   elseif (weatherTypeNum == 26) then
      s = "Neve da moderata a forte"     -- eng: moderate to heavy snow
   elseif (weatherTypeNum == 27) then
      s = "Neve forte a tempesta di neve"     -- eng: heavy snow to snowstorm
   elseif (weatherTypeNum == 28) then
      s = "Nebbioso"                          -- eng: foggy
   elseif (weatherTypeNum == 29) then
      s = "Nebbia densa"                      -- eng: dense fog
   elseif (weatherTypeNum == 30) then
      s = "Nebbia densa e forte"              -- eng: heavy dense fog
   elseif (weatherTypeNum == 31) then
      s = "Nebbia forte"                    -- eng: heavy fog
   elseif (weatherTypeNum == 32) then
      s = "Nebbia densa molto forte"        -- eng: extra-heavy dense fog
   elseif (weatherTypeNum == 33) then
      s = "polvere"                           -- eng: dust
   elseif (weatherTypeNum == 34) then
      s = "sabbia"                            -- eng: sand
   elseif (weatherTypeNum == 35) then
      s = "tempesta di sabbia"                -- eng: sandstorm
   elseif (weatherTypeNum == 36) then
      s = "foschia"                           -- eng: haze
   elseif (weatherTypeNum == 37) then
      s = "foschia moderata"                  -- eng: moderate haze
   elseif (weatherTypeNum == 38) then
      s = "foschia grave"                     -- eng: severe haze
   elseif (weatherTypeNum == 39) then
      s = "foschia pericolosa"                -- eng: hazardous haze
   else
      s = ""
   end
   return s
end

function spellRoughDirection(man)
   local s = ""
   if (man.DirOClock == 0) then
      s = "dritto"                                  -- eng: straight
   elseif (isInRanges(man.DirOClock, 1, 5)) then
      s = "a destra"                                -- eng: right
   elseif (isInRanges(man.DirOClock, 7, 11)) then
      s = "a sinistra"                              -- eng: left
   end
   return s
end

function spellFineDirectionKeep(man)
  local s = ""
   if (man.DirOClock == 0) then
      s = "dritto"                                  -- eng: straight
   elseif (isInRanges(man.DirOClock, 1, 5)) then
      s = "la destra"                                -- eng: right
   elseif (isInRanges(man.DirOClock, 7, 11)) then
      s = "la sinistra"                              -- eng: left
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
         s = conc("leggermente", s)                             -- eng: slight
      end
   elseif ((man.DirOClock == 2) or (man.DirOClock == 10)) then
      s = conc("leggermente", s)                                -- eng: slight
   elseif (isInRanges(man.DirOClock, 4, 5, 7, 8)) then
      s = conc("stretto", s)                                    -- eng: sharp
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
         if (man.project == "zeekr") then
            s = ""
         else
            s = ""
         end
      end
   elseif (man.Stage == 1) then
      if (guidanceLevel == 1) or (guidanceLevel == 3) then
         return s
      end
      if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
         if (man.Distance > 0) then
            s = conc(s, "Tra", spellDistanceAndUnit(man, countryCode, unitArray))        -- eng: In about
         end
      else
        if (man.project == "zeekr") then
         s = "Ora "                                            -- eng: Soon
        else
         s = "Adesso "                                            -- eng: Soon
        end
      end
   else
      if (man.Stage > 2) and ((guidanceLevel == 1) or (guidanceLevel == 3)) then
         return s
      end
      if (man.Distance > 0) then
         s = conc(s, "Tra", spellDistanceAndUnit(man, countryCode, unitArray))        -- eng: In about
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
   local turnStr = ""
   if (man.project == "zeekr") then
    turnStr = "svolta"                                     -- eng: turn
   else
    turnStr = "girare"                                       -- eng: turn
   end
   -- roundabout exit voice
   if ((man.StageClass == "just_before") and (man.Action == "roundabout")) then
      s = conc(s, turnStr)                                       -- eng: turn
      if (man.HintProps.Hint.left_hand_traffic) then
         s = conc(s, "a sinistra")                              -- eng: left
      else
         s = conc(s, "a destra")                                -- eng: right
      end
   elseif (man.HintProps.Hint.along_main_road) then
      s = "Continuare a seguire la strada principale"             -- eng: Keep following main road
   elseif (man.DirOClock == 0 and man.Action == "fork") then
      if (man.project == "zeekr") then
       s = "vai dritto"
      else
       s = "proseguire diritto"                                  -- eng: keep center
      end
   elseif (man.DirOClock == 0) then
      if (man.Action == "exit" and isFromHighway) then
         s = ""
      else
         s = conc(s, "vai", spellRoughDirection(man))              -- eng: go
         if (man.Nth > 0) then
            s = conc(s, "", spellNthStreet(man.Nth, true))
         end
      end
   elseif (man.DirOClock == 6) then
      s = "fare inversione di marcia"                               -- eng: make a u-turn
   else
      if ((man.DirOClock == 1) or (man.DirOClock == 11)) then
         if (man.Action == "exit") then
            if (isFromHighway) then
               s = ""
            else
               s = conc(s, turnStr, spellFineDirection(man))        -- eng: turn
            end
         else
           if (man.project == "zeekr") then
            s = conc(s, "mantieni", spellFineDirectionKeep(man))    -- eng: keep
           else
            s = conc(s, "proseguire", spellFineDirection(man))    -- eng: keep
           end
         end
      else
         if (man.Action == "exit") then
            if (isFromHighway) then
               s = ""
            else
               s = conc(s, turnStr, spellFineDirection(man))           -- eng: turn
            end
         else
            s = conc(s, turnStr, spellFineDirection(man))           -- eng: turn
         end
            -- additional side street count information.
         if ((man.Nth > 0) and (((man.Stage == 1) and (man.StageClass ~= "just_before")) or (man.Stage == 2))) then
         --   s = conc(s, spellNthStreet(man.Nth, false))
         end
      end
   end
   return s
end

function spellRoundaboutDirection(man)
   -- roundabout exit voice
   local s = ""                                 -- eng: turn
   if (man.HintProps.Hint.left_hand_traffic) then
      s = conc(s, "a sinistra")                     -- eng: left
   else
      s = conc(s, "a destra")                       -- eng: right
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
            s = conc(s, "Prendere l’uscita", exitName, "sulla", spellFineDirection(man))
         else
            s = conc(s, " prendere l’uscita", exitName, spellFineDirection(man))     -- eng: take exit
         end
      end
      if (#signPostName > 0) then
         if (man.project == "zeekr") then
            s = conc(s, "verso", signPostName)       -- eng: towards
         else
            s = conc(s, " in direzione", signPostName)       -- eng: towards
         end
      end
      if (man.Action == "exit") then
         local exitStr = "uscire "
         if (man.project == "zeekr") then
           exitStr = "Uscita sulla "
         end
         if (#exitName > 0) then
            s = s
         elseif (#signPostName > 0) then
            s = conc(exitStr, spellFineDirection(man), s)        --exit to the
         else
            s = conc(exitStr, spellFineDirection(man))
         end
      end
      return s
   else
      local isRamp = man.ToProps.Area.ramp
      if (isRamp and #signPostName > 0) then
         s = conc(s, " in direzione", signPostName)
      else
         if (useRoadNameOrNumber) then
            local roadName = getRoadNameOrNumber(man)
            if (#roadName > 0) then
               s = conc(s, " sulla", roadName)               -- eng: onto
            end
         end
      end
   end
   return s
end

function spellIntersectionCount(man)
   -- not count intersection count in manual request.
   local s = spellDirectionPart(man)
   if (man.HintProps.Hint.manual_request) then
      return "" .. s
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
      return "" .. s;
   end
   if (man.ContainTrafficLight and man.TrafficLightIntersectionCount > 3 and man.StageClass ~= "just_before") then
      return "" .. s
   end
   if ((not man.ContainTrafficLight) and man.IntersectionCount > 3 and man.StageClass ~= "just_before") then
      return "" .. s;
   end

   if (man.next ~= nil and man.Stage == 1 and man.StageClass ~= "just_before") then
      return "" .. s
   end

   if (man.StageClass == "just_before"
      and (man.TrafficLightIntersectionCount == 0 and (man.IntersectionCount == 0 or man.IntersectionCount == 255))) then
      return "" .. s
   end

   if (man.Stage == 4 and man.TrafficLightIntersectionCount == 0) then
      return "" .. s;
   end
   if (man.StageClass == "just_before" and man.IntersectionCount == 0) then
      return "" .. s;
   end
   if (man.StageClass == "just_before" and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
      if (man.ContainTrafficLight) then
         if (man.TrafficLightIntersectionCount > 0) then
            s = conc(s, "al", " ", spellOrdinalNumber(man.TrafficLightIntersectionCount + 1), " semaforo")  -- eng: traffic light
         else
            s = s .. " al semaforo"
         end
      else
         s = conc(s, "al", " ", spellOrdinalNumber(man.IntersectionCount + 1), "incrocio")       -- eng: intersection
      end
   elseif (man.ContainTrafficLight and (not isHighway)) then
      if ((guidanceLevel == 1) and (man.StageClass == "just_before")) or ((guidanceLevel == 3) and (man.StageClass == "just_before")) or (guidanceLevel == 2) then
         if (man.TrafficLightIntersectionCount > 0) then
            s = conc(s, "al", " ", spellOrdinalNumber(man.TrafficLightIntersectionCount + 1), " semaforo")   -- eng: traffic light
         else
            s = s .. " al semaforo"
         end
      end
   elseif (isHighway and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
      s = conc(s, "al", " ", spellOrdinalNumber(man.IntersectionCount + 1), "incrocio")       -- eng: intersection
   end
   return s
end

function spellPrepositionAndGender(props)
   local s = ""
   if (props.Preposition == "into") or (props.Preposition == "onto") then
      s = "sulla"                               -- eng: onto
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
      s = "prima"                     -- eng: first
   elseif (number == 2) then
      s = "seconda"                   -- eng: second
   elseif (number == 3) then
      s = "terza"                     -- eng: third
   elseif (number == 4) then
      s = "quarta"                    -- eng: fourth
   elseif (number == 5) then
      s = "quinta"                    -- eng: fifth
   elseif (number == 6) then
      s = "sesta"                     -- eng: sixth
   elseif (number == 7) then
      s = "settima"                   -- eng: seventh
   elseif (number == 8) then
      s = "ottava"                    -- eng: eighth
   elseif (number == 9) then
      s = "nona"                      -- eng: ninth
   elseif (number == 10) then
      s = "decima"                    -- eng: tenth
   elseif (number == 11) then
      s = "undicesima"                -- eng: eleventh
   elseif (number == 12) then
      s = "dodicesima"                -- eng: twelfth
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
         s = hour .. " ore"                         -- eng: hours
      elseif (hour == 1) then
         s = hour .. " ora"                         -- eng: hour
      end

      if (minutes > 1) then
         s = s .. " " .. minutes .. " minuti"       -- eng: minutes
      elseif(minutes == 1) then
         s = s .. " " .. minutes .. " minuto"       -- eng: minute
      end
   end
   if (#s > 0) then
      s = "Puoi ottenere la destinazione in " .. s  -- eng: You can get destination in
   end
   return s
end

function spellTime(timeVal, isTwelveHoursClock, arrivalTimeMode, projectName)
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
            s = "mezzanotte"                        -- eng: midnight
         elseif (t.min == 0) and (t.hour == 1) then
            s = "una"
         elseif (t.min == 0) and isInRanges(t.hour, 2, 11) then
            s = hour                        -- eng: o'clock
         elseif (t.min == 0) and (t.hour == 12) then
            s = "mezzogiorno"               --noon
         elseif (t.min == 0) and isInRanges(t.hour, 13, 23) then
            s = hour                        -- eng: o'clock
         elseif (t.hour == 0) and isInRanges(t.min, 1, 59) then
            s = "0 e " .. t.min
         elseif (t.hour == 1) and isInRanges(t.min, 1, 59) then
            s = "una e " .. t.min
         else
            s = hour .. " e " .. t.min       -- eng: null / null
         end

         if (isTwelveHoursClock) then
            local zeroClock = false
            local twelveHoursClockMark = string.lower(os.date("!%p", timeVal))
            if (t.min == 0) and (t.hour == 0) then
               s = "mezzanotte"                     -- eng: midnight
               zeroClock = true
            elseif (t.min == 0) and (t.hour == 12) then
               s = "mezzogiorno"                    -- eng: noon
               zeroClock = true
            elseif (t.min == 0) then
               s = hour ..  ""
            elseif (isInRanges(t.min, 1, 9)) then
               s = hour .. ":" .. t.min    -- eng: o / null
            else
               s = hour .. ":" .. t.min    -- eng: null / null
            end
            if (twelveHoursClockMark == "am") and (not zeroClock) then
               s = s .. " mattino"                  -- eng: a m
            elseif (twelveHoursClockMark == "pm") and (not zeroClock) then
               s = s .. " pomeriggio"               -- eng: p m
            end
         end
      end
   end
   if (#s > 0) then
     if (projectName == "zeekr") then
      s = "Tempo di arrivo stimato " .. s
     else
      s = "Orario di arrivo circa " .. s          -- eng: Estimated time of arrival
     end
   end
   return s
end

function spellNthStreet(nth, isStraight)
   local s = ""
   if (nth == 1) then
      s = " a sinistra"                       -- eng: the left
   elseif (nth == 2) then
      s = " al centro"                        -- eng: the center
   elseif (nth == 3) then
      s = " a destra"                         -- eng: the right
   end
   if (isStraight) then
      s = "" .. s .. " strada"                -- eng: to / road
   else
      s = "e prendi la strada" .. s        -- eng: and take / road
   end
   return s
end

function processIntersectionAction(man)
   local s = spellIntersectionCount(man)
   if (man.StageClass == "just_before") then
      if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
         s = " " .. s
      else
        if (man.project ~= "zeekr") then
         s = s .. " qui"                                             -- eng: Here
        end
      end
   elseif (man.Stage == 1) then
      if (man.IntersectionCount ~= nil and man.IntersectionCount > 0 and man.IntersectionCount < 255) then
         s = conc("Tra", spellDistanceAndUnit(man, countryCode, unitArray), s)        -- eng: In about
      else
         s = "Adesso " .. s                                            -- eng: Soon
      end
   else
      s = conc("Tra", spellDistanceAndUnit(man, countryCode, unitArray), s)        -- eng: In about
   end
   s = conc(s, "", spellNamesPart(man, true))
   return s
end

function spellDirectionPartWITHsoon(man)
   local s = ""
   local isFromHighway = man.FromProps.Area.motorway
   if (isFromHighway == nil) then
      isFromHighway = false
   end

   -- roundabout exit voice
   if ((man.StageClass == "just_before") and (man.Action == "roundabout")) then
      s = conc(s, "Adesso girare")                                       -- eng: turn
      if (man.HintProps.Hint.left_hand_traffic) then
         s = conc(s, "a sinistra")                              -- eng: left
      else
         s = conc(s, "a destra")                                -- eng: right
      end
   elseif (man.HintProps.Hint.along_main_road) then
      s = "Continuare a seguire la strada principale"             -- eng: Keep following main road
   elseif (man.DirOClock == 0 and man.Action == "fork") then
      s = "Mantieniti al centro"                                  -- eng: keep center
   elseif (man.DirOClock == 0) then
      if (man.Action == "exit" and isFromHighway) then
         s = ""
      else
         s = conc(s, "vai", spellRoughDirection(man))              -- eng: go
         if (man.Nth > 0) then
            s = conc(s, "", spellNthStreet(man.Nth, true))
         end
      end
   elseif (man.DirOClock == 6) then
      s = "fare inversione di marcia"                               -- eng: make a u-turn
   else
      if ((man.DirOClock == 1) or (man.DirOClock == 11)) then
         if (man.Action == "exit") then
            s = conc(s, "Adesso girare", spellFineDirection(man))        -- eng: turn
         else
            s = conc(s, "mantieniti", spellFineDirection(man))    -- eng: keep
         end
      else
         s = conc(s, "Adesso girare", spellFineDirection(man))           -- eng: turn
            -- additional side street count information.
         if ((man.Nth > 0) and (((man.Stage == 1) and (man.StageClass ~= "just_before")) or (man.Stage == 2))) then
            s = conc(s, "", spellNthStreet(man.Nth, false))
         end
      end
   end
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
         if (spellDirectionPart(man.previous) == spellDirectionPart(man)) then
            s = conc(s, "di nuovo")                        -- eng: again
         else
            s = spellDirectionPartWITHsoon(man)                       -- eng: soon
            s = conc(s, spellNamesPart(man, false))
         end
      elseif ((isAvailable(man.previous))
         and  (man.previous.Action == man.Action)
         and  ((not man.previous.HintProps.Hint.along_main_road) and man.HintProps.Hint.along_main_road)) then
         s = conc(s, "")
      elseif (     (isAvailable(man.previous))
         and   (man.previous.Action == man.Action)
         and   (man.previous.DirOClock == man.DirOClock)) then
         s = conc(s, "di nuovo")                        -- eng: again
      elseif ((isAvailable(man.previous))
         and  (man.previous.Action == man.Action)
         and  (spellDirectionPart(man.previous) == spellDirectionPart(man))) then
         s = conc(s, "di nuovo")                        -- eng: again
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
         s = "Prendi l'uscita " .. spellOrdinalNumber(man.Nth) .. " e lascia la rotonda"             -- eng: Take the / exit and leave the roundabout
         return s
      end
   end
   local leaveRoundaboutStr = "Abbandonare la rotatoria alla "
   if (man.project == "zeekr") then
     leaveRoundaboutStr = "Esci dalla rotonda alla "
   end
   if (man.Action == "roundabout" and ((man.Stage == 2 or man.Stage == 3) or (man.Stage == 1 and man.DerivedBegin))) then
      --s = "Tra " .. spellDistanceAndUnit(man, countryCode, unitArray) .. " entra la rotonda"                         -- eng: In about / enter the roundabout
      if (man.project == "zeekr" and man.Stage == 3) then
         s = "Rotonda avanti"
      else
         s = leaveRoundaboutStr .. spellOrdinalNumber(man.Nth) .. " uscita"             -- eng: then take the / exit and leave the roundabout
      end
   elseif (man.StageClass == "roundabout_export") then
      if (guidanceLevel == 1) or (guidanceLevel == 3) then
         s = ""
      else
         s = leaveRoundaboutStr .. spellOrdinalNumber(man.CurrentRoundaboutExitNum) .. " uscita"        -- eng: Go through the / exit
         if (man.CurRoundaboutExitPhaseCombinedWithNext) then
         -- eng: then take the / exit and leave the roundabout
            s = concat(s, ", quindi prendi l'uscita ", spellOrdinalNumber(man.CurrentRoundaboutExitNum + 1)) .. " e lascia la rotonda"
         end
         man._NoCombination = true
      end
   elseif (man.StageClass == "roundabout_exit") then
      if (man.project ~= "zeekr" or guidanceLevel > 1) then
         s = "Adesso " .. spellRoundaboutDirection(man)             -- eng: and leave the roundabout
      end
   end
   return s
end

function processRoundaboutActionCombined(man)
   local s = spellDistancePart(man)
   s = conc(s, "lasciare la rotonda all'uscita", spellOrdinalNumber(man.Nth))   -- eng: leave the roundabout at the

   if ((#s > 0) and (isAvailable(man.previous)) and (man.previous.Stage == 2)) then
      s = conc(s, "presto")                           -- eng: soon
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

   if (man.FromProps.Area.crime and man.Stage == 1) then
      s = ""
   elseif (man.Stage == 1) then                 
      if (man.project == "zeekr") then
        s = "Hai raggiunto"
      else
        s = "Avete raggiunto " -- eng: You have reached
      end
   else
      s = spellDistancePart(man)
      if (onlyDirection) then
         s = ""
      end
      if (man.project == "zeekr") then
        s = s .. " avrai raggiunto "
      else
        s = s .. " avete raggiunto "                       -- eng: you will have reached
      end
   end

   s = concat(s, " ", spellDestinationName(man))
   if ((#s > 0) and (isAvailable(man.previous)) and (man.previous.Stage == 2)) then
         s = conc(s, "presto")                        -- eng: soon
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
      s = "il traghetto"                                                       -- eng: the ferry terminal
   elseif (man.FromProps.Area.ipd) then
      s = conc(s, "la stazione di ricarica, si prega di caricare a", spellNumber(man.Nth))  -- eng: the charging station, please charge to
      s = s .. "%"
   elseif (man.FromProps.Area.crime and man.Stage == 1) then
      -- eng: Approaching Setel gas station, do you need Setel service?
      s = "Avvicinandosi alla stazione di servizio, hai bisogno di iniziare il servizio di rifornimento?"
   elseif (man.Nth == 0) then
      s = "la destinazione"                                     -- eng: the destination
      if (man.DirOClock == 9) then
         s = concat(s, ". ", "Si trova sulla sinistra")                    -- eng: It is on the left
      elseif (man.DirOClock == 3) then
         s = concat(s, ". ", "Si trova sulla destra")                  -- eng: It is on the right
      end
   elseif (isInRanges(DestNumber, 1, 100)) then
      s = conc(s, "la destinazione intermedia", spellNumber(DestNumber))    -- eng: the intermediate destination
      if (man.DirOClock == 9) then
         s = concat(s, ". ", "Si trova sulla sinistra")                    -- eng: It is on the left
      elseif (man.DirOClock == 3) then
         s = concat(s, ". ", "Si trova sulla destra")                  -- eng: It is on the right
      end
   end

   if ((man.Nth == 0) and (man.Stage == 1) and (not isAvailable(man.previous)) ) then
      s = concat(s, ". ")            -- eng: Thank you for driving
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
   local tollStr = "Casello avanti"
   if (man.project == "zeekr") then
     tollStr = "Casello di pedaggio più avanti"
   end
   if (guidanceLevel == 1) then
      if (man.Stage == 2) then
         s = tollStr                  -- eng: Soon reach toll gate
      end
   elseif (guidanceLevel == 3) then
      if (man.Stage == 2) then
         s = tollStr
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 3) ) then
         s = tollStr
      end
   elseif (guidanceLevel == 2) then
      if ((man.Stage == 1) and (man.StageClass ~= "just_before")) then
         s = tollStr                  -- eng: Soon reach toll gate
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 2) ) then
         s = tollStr                            -- eng: Toll gate ahead
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
         s = "Tunnel avanti"                         -- eng: Soon tunnel ahead
      end
   elseif (guidanceLevel == 2) then
      if ((man.Stage == 1) and (man.StageClass ~= "just_before")) then
         s = "Tunnel avanti"                         -- eng: Soon tunnel ahead
      elseif (man.project == "zeekr" and man.Stage == 2 and guidanceLevel == 2) then
         s = "Tunnel avanti"                         -- eng: Soon tunnel ahead
      elseif ( (man.HintProps.Hint.manual_request) or (man.previous ~= nil) or (man.Stage > 2) ) then
         s = "Tunnel avanti"                           -- eng: tunnel ahead
      end
   end
   return s
end

function processEnterActionWarning(man, AnnouncementIsNotEmpty)
   local s = ""
   if (man.project == "zeekr") then
      s = "Attenersi alle norme di circolazione vigenti"
     else
      s = "Si prega di osservare le norme sul traffico in vigore"                -- eng: Please follow actual traffic regulations
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
         s = conc(s, "presto")                                        -- eng: soon
   end
   return s
end

function processMotorwayEnterAction(man)
   local s = spellDistancePart(man)
   s = conc(s, "", spellDirectionPart(man))
   s = conc(s, "", "")       -- eng: you have reached the highway entrance
   s = conc(s, "", spellMotorWayEnterNamesPart(man, true))
   return s
end


function spellMotorWayEnterNamesPart(man, useRoadNameOrNumber)
   local s = ""
   local signPostName = getDirectionName(man)
   -- motorway entry
   if (man.HintProps.Hint.motorway_entry) then
       if (#signPostName > 0) then
         s = conc(s, "in direzione", signPostName)                  -- eng: towards
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
      s = "Attenzione! Fusione del traffico"                          -- eng: Be careful of merging traffic
      if ((man.DirOClock ~= 0) and (man.DirOClock ~= 6)) then
         s = conc(s, "dal", spellRoughDirection(man))                 -- eng: from the
      end
   end
   return s
end


function processFollowRoadAction(man)
   local s = ""
   if (man.Distance > 0) then
      s = conc(s, "per", spellDistanceAndUnit(man, countryCode, unitArray))           -- eng: Within about
   end
   s = conc("Seguire la strada", s)                            -- eng: Please follow current road
   return s
end

function processDPTrafficLightAction(man)
   local s = "Incrocio semaforico più avanti, per favore vai dritto"  -- eng: Traffic light intersection ahead, please go straight
   if (man.project == "zeekr") then
     s = "Incrocio con semaforo più avanti, prosegui dritto."
   end
   if (man.Stage == 1 and man.TrafficLightIntersectionCount > 0) then
      -- eng: Go straight through the / traffic light
      s = "Proseguire dritto attraverso il " .. spellOrdinalNumber(man.TrafficLightIntersectionCount) .. " semaforo"
   end
   return s
end

function processDPRecomLaneAction(man)
   local s = ""
   local takeStr = "Prendere"
   if (man.project == "zeekr") then
     takeStr = " Prendi"
   end
   if (man.IsContiLaneVoice) then
      if (man.BestRecomNum > 0) then
         if (not man.IsManeuver) then
            local dir = spellDirection(man.RecomLaneDir)
            if (dir ~= "") then
               s = conc(dir, ".")
            end
         end
         s = conc(s, "Raccomandazione:")            -- eng: Recommend taking the
         s = s .. takeStr
      else
         s = conc(s, "Per favore prendi le")                -- eng: Please take the
      end
      local pos = ""
      if ((man.IsInc2nd) and (man.IsInc3rd)) then
         s = conc(s, "la seconda o terza corsia zweite oder dritte Spur")              -- eng: second and third lanes
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         s = conc(s, pos)
      elseif (man.IsInc2nd) then
         s = conc(s, "la seconda corsia")                      -- eng: second lane
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         s = conc(s, pos)
      elseif (man.IsInc3rd) then
         s = conc(s, "la terza corsia")                        -- eng: third lane
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         s = conc(s, pos)
      elseif (((man.BestRecomNum == 1) or (man.RecomNum == 1)) and (man.RecomLanePos == 1)) then
         s = conc(s, "la corsia più a sinistra")               -- eng: leftmost lane
      elseif (((man.BestRecomNum == 1) or (man.RecomNum == 1)) and (man.RecomLanePos == 2)) then
         s = conc(s, "la corsia più a destra")                 -- eng: rightmost lane
      else
         pos = spellPosition(man.IsContiLaneVoice, man.RecomLanePos)
         if (man.BestRecomNum > 1) then
            local bestNumber = convToString(man.BestRecomNum)
            s = conc(s, "una delle", bestNumber, "corsie", pos)               -- eng: lanes
         elseif (man.RecomNum > 1) then
            local number = convToString(man.RecomNum)
            s = conc(s, "una delle", number, "corsie", pos)                   -- eng: lanes
         else
            s = conc(s, "corsia", pos)                           -- eng: lane
         end
      end
   else
      if (not man.IsManeuver) then
         local dir = spellDirection(man.RecomLaneDir)
         if (dir ~= "") then
            s = conc(dir, ".")
         end
      end
      s = conc(s, takeStr)                    -- eng: Please take the
      local pos = spellPosition(man.isContiLaneVoice, man.RecomLanePos)
      if (man.RecomNum > 1) then
         local number = convToString(man.RecomNum)
         s = conc(s, "una delle", number, "corsie", pos)                       -- eng: lanes
      else
         s = conc(s, "la corsia", pos)                               -- eng: lane
      end
   end
   return s
end

function spellDirection(dir)
   local s = ""
   if (dir == 1) then
      s = "girare stretto a sinistra"       -- eng: Turn sharp left
   elseif (dir == 2) then
      s = "girare a sinistra"                   -- eng: Turn left
   elseif (dir == 3) then
      s = "girare leggermente a sinistra"            -- eng: Turn slight left
   elseif (dir == 4) then
      s = "girare stretto a destra"            -- eng: Turn sharp right
   elseif (dir == 5) then
      s = "girare a destra"                  -- eng: Turn right
   elseif (dir == 6) then
      s = "girare leggermente a destra"           -- eng: Turn slight right
   elseif (dir == 7) then
      s = "proseguire diritto"              -- eng: Go straight on
   elseif (dir == 8) then
      s = "fare inversione di marcia"       -- eng: Make a u-turn
   end
   return s
end

function spellPosition(isContiLaneVoice, position)
   local s = ""
   if (isContiLaneVoice) then
      if (position == 1) then
         s = "da sinistra"           -- eng: on the left
      elseif (position == 2) then
         s = "da destra"             -- eng: on the right
      elseif (position == 3) then
         s = "al centro"                -- eng: on the center
      end
   else
      if (position == 1) then
         s = "di sinistra"                 -- eng: left
      elseif (position == 2) then
         s = "di destra"                   -- eng: right
      elseif (position == 3) then
         s = "centrale"                   -- eng: center
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
   if ((distKm >= 1) and testDistance3(distM, nil) and (spellDistanceAndUnit(man, countryCode, unitArray) ~= "")) then
     if (man.project == "zeekr") then
      s = conc("Tra circa", spellDistanceAndUnit(man, countryCode, unitArray))                              -- eng: In
     else
      s = conc("Tra", spellDistanceAndUnit(man, countryCode, unitArray))                              -- eng: In
    end
   end
   s = conc(getAreaType(man.AreaType, man), s)
   if ((nextDistKm >= 1) and testDistance4(nextDistM, nil) and (spellDistanceAndUnit(man, countryCode, unitArray, true) ~= "")) then
     if (man.project == "zeekr") then
       s = conc(s, ". La seguente si trova a circa", spellDistanceAndUnit(man, countryCode, unitArray, true))   -- eng: The next one is in
     else
       s = conc(s, ". La seguente si trova a", spellDistanceAndUnit(man, countryCode, unitArray, true))   -- eng: The next one is in
     end
   end
   return s
end

function getAreaType(type, man)
   local s = ""
   if (type == 1000) then
     s = "area di servizio"         -- eng: service area
     if (man.project == "zeekr") then
      s = "Area di riposo"
     end
   elseif (type == 1001) then
     s = "area di parcheggio"       -- eng: parking area
   end
   return s
end

function processWNHighAccidentAreaAction(projectName)
   local s = "Zona ad alto rischio di incidenti avanti. Guidare con attenzione"  -- eng: High accident area ahead. Please drive carefully
   if (projectName == "zeekr") then
     s = "Area ad elevato rischio di incidenti più avanti. Guidare con prudenza"
   end
   return s
end

function processWNSchoolZoneAction(projectName)
   local s = "Zona scolastica avanti. Guidare con attenzione"             -- eng: School zone ahead. Please drive carefully
   if (projectName == "zeekr") then
     s = "Zona scolastica più avanti. Guidare con prudenza"
   end
   return s
end

function processWNRailwayAction(projectName)
   local s = "Passagio a livello avanti. Guidare con attenzione"                    -- eng: Railway ahead. Please drive carefully
   if (projectName == "zeekr") then
     s = "Passaggio a livello più avanti. Guidare con prudenza"
   end
   return s
end

function processWNDestParkingAction(man)
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel == 2) then
      s = "Trovato un parcheggio vicino alla destinazione, toccare su Vai"          -- eng: The destination parking lot has been recommended for you, click Go
      if (man.project == "zeekr") then
         s = "Rilevato parcheggio nei pressi della destinazione, premi Vai"
      end
   end
   return s
end

function processWNDestChargingStationAction(projectName)
   local s = "Stazioni di ricarica trovate vicino alla destinazione"               -- eng: Recommendations of charging stations near the destination
   if (projectName == "zeekr") then
     s = "Rilevate stazioni di ricarica nei pressi della destinazione"
   end
   return s
end

function processWNTunnelAction()
   local s = "Tunnel avanti"                                                      -- eng: Tunnel ahead
   return s
end

function processWNGpsAction()
   -- eng: The current GPS signal is weak, and the position update may not be timely
   local s = "Il segnale GPS attuale è debole, e l'aggiornamento della posizione potrebbe non essere tempestivo"
   return s
end

function processWNLaneMergeAction(man)
   local s = ""
   if (man.LaneMergeType == 1) then
      s = "da sinistra"                                      -- eng: Left side
   elseif (man.LaneMergeType == 2) then
      s = "da destra"                                        -- eng: Right side
   end
   if (man.project == "zeekr") then
    s = conc("Traffico in entrata da", s, "più avanti. Guidare con attenzione")   -- eng: merger ahead. Please drive carefully
   else
    s = conc("Fusione di trafico", s, "avanti. Guidare con attenzione")   -- eng: merger ahead. Please drive carefully
   end
   return s
end

function processWNTrafficCameraAction(man)
   local s = ""
   if (man.CameraType == 1) then
      s = "Autovelox"                                    -- A speed camera is ahead
   elseif (man.CameraType == 7) then
      s = "Autovelox e telecamera a luci rosse"                      -- Speed and red light cameras are
      if (man.project == "zeekr") then
       s = "Autovelox e T-Red"
      end
   elseif (man.CameraType == 3) then
      s = "Telecamera a luci rosse"                             -- A red light camera is
      if (man.project == "zeekr") then
       s = "T-Red"
      end
   elseif (man.CameraType == 11) then
      s = "Safety Tutor" -- You are driving into a speed measurement section
      if (man.project == "zeekr") then
       s = "Zona di controllo della velocità media"
      end
   elseif (man.CameraType == 12) then
      s = "Fine della zona di Safety Tutor tra"    -- You are leaving the speed measurement section
      if (man.project == "zeekr") then
       s = "Fine della zona di controllo della velocità media tra"
      end
   else
      return s
   end
   local aheadStr = "davanti a voi"
   if (man.project == "zeekr") then
     aheadStr = "più avanti"
   end
   local currentDis = man.Distance
   if (testDistance(currentDis, nil) and (spellDistanceAndUnit(man, countryCode, unitArray) ~= "")) then
      if (man.CameraType == 1) then
         s = conc(s, spellDistanceAndUnit(man, countryCode, unitArray), aheadStr)
      elseif (man.CameraType == 12) then
         s = conc(s, spellDistanceAndUnit(man, countryCode, unitArray))
      else
         s = conc(s, spellDistanceAndUnit(man, countryCode, unitArray), aheadStr)               -- ahead
      end
   else
      if (man.CameraType ~= 1) then
         s = conc(s, aheadStr)
      end
   end
   local speedLimit = man.SpeedLimit
   local unit = "km/h"
   if (speedLimit > 0) then
      if (man.DistUnit == "mile" or man.DistUnit == "quartermile" or man.DistUnit == "yard" or man.DistUnit == "foot") then
         speedLimit = getIntNumber(speedLimit / 1.609344 / 5 + 0.5) * 5;
         unit = "mph"
      end
   end
   local limitedSpeed = convToString(speedLimit)
   if ((man.CameraType == 1) and (limitedSpeed ~= "0")) then
      s = conc(s, ". Limite di velocità", limitedSpeed, unit)                -- The limit is x km/h
   elseif ((man.CameraType == 7) and (limitedSpeed ~= "0")) then
      s = conc(s, ". Limite di velocità", limitedSpeed, unit)
   elseif (man.CameraType == 3) then
      s = conc(s, ".")                 -- Please drive carefully
   elseif ((man.CameraType == 11) and (limitedSpeed ~= "0")) then
      s = conc(s, ". Limite di velocità", limitedSpeed, unit)
   end
   return s
end

function processWNCurveAction(man)
   local s = ""
   if (man.IsContiCurve) then
      s = "Doppia curva avanti. Guidare con attenzione"    -- eng: Continuous curve ahead. Please drive carefully
      if (man.project == "zeekr") then
        s = "Tornanti più avanti. Guidare con prudenza"
      end
   else
      s = "Curva avanti. Guidare con attenzione"             -- eng: Curve ahead. Please drive carefully
      if (man.project == "zeekr") then
        s = "Curve più avanti. Guidare con prudenza"
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
         s = conc("Tra", spellDistanceAndUnit(man, countryCode, unitArray))     -- eng: In about
      end
      s = conc(s, "", content)                                     -- eng: there is a
   end
   return s
end

function processWNOverspeedAction(man)
   local s = ""
   local speedLimit = man.SpeedLimit
   local carSpeed = man.CarSpeed
   local speedLimitStr = ""
   local carSpeedStr = ""
   local unit = "km/h"
   if (man.DistUnit == "mile") then
      speedLimit = getIntNumber(speedLimit / 1.609344 / 5 + 0.5) * 5;
      unit = "mph"
   end
   speedLimitStr = convToString(speedLimit)
   carSpeedStr = convToString(carSpeed)
   -- eng: Speed limit is / km/h. You are exceeding the speed limit
   s = "Sta superando il limite di velocità di " .. speedLimitStr .. " " .. unit
   return s
end

function processWNBorderAction(man)
   local s = ""
   if (man.BorderName ~= "") then
      s = conc("Entrata a", man.BorderName)                    -- eng: Entering
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
         -- eng: The current lane is forbidden from / to
         s = "La corsia attuale è interdetta da " .. spellTimeForInappLane(man.FromHour, man.FromMinutes) .. " a " .. spellTimeForInappLane(man.ToHour, man.ToMinutes)
      end
   else
      -- other reasons
      if (spellInappLaneReason(man.InappLaneReason) ~= "") then
         s = "La corsia attuale è dedicata a " .. spellInappLaneReason(man.InappLaneReason)       -- eng: The current lane is dedicated to
      end
   end
   return s
end

function processWNNarrowLaneAction(man)
   local s = ""
   if (man.NarrowLaneVoiceType == 1) then
      if (man.NarrowLaneDir == 1) then
         s = "Avanti a sinistra si restringe di corsia"               -- eng: Forward left lane narrows
      elseif (man.NarrowLaneDir == 2) then
         s = "Avanti a destra si restringe di corsia"                 -- eng: Forward right lane narrows
      elseif (man.NarrowLaneDir == 3) then
         s = "Avanti entrambe le corsie strette"                      -- eng: Forward both lanes narrow
      else
         return s
      end
      s = s .. ". Guidare con attenzione"                                 -- eng: Please drive carefully
   elseif (man.NarrowLaneVoiceType == 2) then
      if (man.NarrowLaneDir == 1) then
         s = "a sinistra"                                          -- eng: left side
      elseif (man.NarrowLaneDir == 2) then
         s = "a destra"                                            -- eng: right side
      else
         return s
      end
      -- eng: The road ahead becomes narrower. Please change lanes to the
      s = "La strada davanti diventa più stretta. Si prega di cambiare corsia per il " .. s
   end
   return s
end

function processEVLowBattery(man)
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel > 1) then
      s = "Batteria scarica. Ricaricare"                             -- eng: Low battery, please charge
   end
   return s
end

function processEVFewChargingStations(man)
   local s = ""
   if (man.GuidanceLevel > 1) then
      s = "Ci sono poche stazioni di ricarica. Ricaricare in tempo"     -- eng: There are few charging stations. Please charge timely
      if (man.project == "zeekr") then
         s = "Poche stazioni di ricarica presenti. Ricaricare rapidamente"
      end
   end
   return s
end

function processSRPSuccess()
   local s = "Pianificazione itinerario di ricarica intelligente completato"
   return s
end

function processSRPFail(projectName)
   local s = "Livello della batteria insufficiente per arrivare a destinazione. Nessuna stazione di carica trovata lungo il percorso, ricarica per tempo"    -- eng: Battery level not sufficient to arrive at the destination. No charging station found along the route, please charge timely
   if (projectName == "zeekr") then
     s = "Livello di batteria non sufficiente per arrivare a destinazione. Nessuna stazione di ricarica rilevata lungo il percorso. Ricaricare rapidamente."
   end
   return s
end

function processBeepDRGPopup(man)
   local s = ""
   if (man.project ~= "zeekr" or man.GuidanceLevel > 1) then
      local minutes = convToString(man.Minutes)
      if (man.project == "zeekr") then
         s = conc("Rilevato nuovo percorso, il", minutes, "minuti verrà salvato")   -- eng: A new route found / minutes will be saved
      else
         s = conc("È stato trovato un nuovo percorso. Si risparmia", minutes, "minuti")   -- eng: A new route found / minutes will be saved
      end
   end
   return s
end

function spellTimeForInappLane(hour, min)
   local s = ""
   if (isNumber(hour) and isNumber(min)) then
      if (min == 0) and (hour == 0) then
         s = "mezzanotte"                                             -- eng: midnight
      elseif (min == 0) and isInRanges(hour, 1, 11) then
         s = hour .. " in punto"                                      -- eng: o'clock
      elseif (min == 0) and (hour == 12) then
         s = "mezzogiorno"                                            -- eng: noon
      elseif (min == 0) and isInRanges(hour, 13, 23) then
         s = hour .. " in punto"                                      -- eng: o'clock
      elseif (isInRanges(min, 1, 9)) then
         s = hour .. " ore " .. t.min .. " minuti "                   -- eng: o / null
      else
         s = hour .. " : " .. min .. " "                              -- eng: null / null
      end
   end
   return s
end

function spellInappLaneReason(reason)
   local s = ""
   if (reason == 2) then
      s = "HOV"                                     -- eng: HOV
   elseif (reason == 3) then
      s = "taxi"                                    -- eng: taxis
   elseif (reason == 4) then
      s = "autobus"                                 -- eng: buses
   elseif (reason == 5) then
      s = "ambulanze"                               -- eng: ambulances
   end
   return s
end

function getTrafficContent(kind)
   local s = ""
   if (kind == 0) then
      s = "ingorgo"                        -- eng: congestion
   elseif (kind == 1) then
      s = "ostacoli sulla carreggiata"                                -- eng: obstacle
   elseif (kind == 2) then
      s = "incidente"                               -- eng: accident
   elseif (kind == 3) then
      s = "lavori stradali"                         -- eng: road work
   elseif (kind == 4) then
      s = "restringimento della corsia"                          -- eng: narrow lanes
   elseif (kind == 5) then
      s = "strada sdrucciolevole"                   -- eng: slippery road
   elseif (kind == 6) then
      s = "acquaplaning"                            -- eng: aquaplaning
   elseif (kind == 7) then
      s = "incendio"                                -- eng: fire
   elseif (kind == 8) then
      s = "cedimento stradale"                      -- eng: road subsidence
   elseif (kind == 9) then
      s = "veicolo in panne"                    -- eng: disabled vehicle
   elseif (kind == 10) then
      s = "strada chiusa"                           -- eng: road closed
   elseif (kind == 11) then
      s = "condizioni di forte maltempo"            -- eng: extreme weather conditions
   elseif (kind == 12) then
      s = "persone sulla carreggiata"           -- eng: reckless persons
   elseif (kind == 13) then
      s = "controllo dell'altezza scattato"   -- eng: over height warning system triggered
   elseif (kind == 14) then
      s = "Regole del traffico cambiate"            -- eng: traffic regulations changed
   elseif (kind == 15) then
      s = "grande evento"                       -- eng: major event
   elseif (kind == 16) then
      s = "servizio non disponibile"               -- eng: service not usable
   elseif (kind == 17) then
      s = "veicolo lento"                           -- eng: slow moving vehicle
   elseif (kind == 18) then
      s = "fine periculosa dell'ingorgo"           -- eng: dangerous end of queue
   elseif (kind == 19) then
      s = "rischio di incendi"                      -- eng: risk of fire
   elseif (kind == 20) then
      s = "ritardo"          -- eng: extra security measures
   elseif (kind == 21) then
      s = "controllo della polizia"              -- eng: police checkpoint
   elseif (kind == 22) then
      s = "attrezzatura stradale malfunzionante"    -- eng: malfunctioning roadside equipment
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
            s = "Attenersi alle norme di circolazione vigenti"
         end
      else
         s = "Si prega di osservare le norme sul traffico in vigore"                -- eng: Please follow actual traffic regulations
      end
   elseif (man.Action == "enter_better_route") then
      if (guidanceLevel > 1) then
         s = "È entrato un percorso migliore"                      -- eng: Have entered the better route
      else
         s = ""
      end
   elseif (man.Action == "blocked_road") then
      if (guidanceLevel > 1) then
         s = "Strada bloccata davanti, guida con nuovo percorso"   -- eng: Road blocked ahead, guide with new route
      else
         s = ""
      end
   elseif (man.Action == "time_regulation_road") then
      if (guidanceLevel > 1) then
         s = "Regolazione del tempo in anticipo, guida con nuovo percorso"   -- eng: Time regulation ahead, guide with new route
      else
         s = ""
      end
   elseif (man.Action == "no_guidance_active") then
      s = "Imposta il percorso"                                 -- eng: Please set route
   elseif (man.Action == "drive_to_route") then
      -- eng: Please follow the route. Guidance to destination is then starting
      s = "Si prega di guidare lungo la strada corrente. Viene avviata la guida alla destinazione"
   elseif (man.Action == "ti_route_changed") then
      s = "Percorso ottimale viene aggiornato"                  -- eng: Optimal route is updated
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
      if ((getIntNumber(man.TotalNumberOfDestinations) == 1) or (getIntNumber(man.DestinationIndex) == 0)) then
         if (man.project == "zeekr") then
          s = conc(s, " La navigazione è iniziata")
         else
          s = conc(s, " Viene avviata la guida alla destinazione")     -- eng: Guidance to destination is starting
         end
      elseif (isInRanges(DestNumber, 1, 5)) then
        if (man.project == "zeekr") then
         s = conc(s, "La navigazione in direzione della ", spellNumber(DestNumber))
         s = s .. " tappa è iniziata"
        else
         s = conc(s, "Viene avviata la guida alla destinazione intermedia", spellNumber(DestNumber))  -- eng: Guidance to intermediate destination / is starting
        end
      end
      local aTime = spellTime(man.ArrivalTime, man.HintProps.Hint.clock_format_12_hour, man.HintProps.ArrivalTimeMode, man.project)
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
        s = conc(s , " Condizioni meteo a destinazione: ")    -- eng: Destination Weather is
      else
        s = conc(s , " Meteo a destinazione: ")    -- eng: Destination Weather is
      end
      for key, weatherType in pairsSortedByKeys(man.WeatherTypes) do
         if (weatherType ~= nil) then
            if (weatherTypeCount > 1) then
               s = conc(s, "")                      -- eng: to
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
         if (man.project == "zeekr") then
          s = "Procedere con il percorso evidenziato"              -- eng: Please follow the road
         else
          s = "Seguire la strada"              -- eng: Please follow the road
         end
      end

      local aTime = spellTime(man.ArrivalTime, man.HintProps.Hint.clock_format_12_hour, man.HintProps.ArrivalTimeMode, man.project)
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
   s = "Presto continua a seguire la strada principale"     -- eng: Soon Keep following main road
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
      s = processWNHighAccidentAreaAction(man.project)
   elseif (man.Action == "wn_school_zone") then
      s = processWNSchoolZoneAction(man.project)
   elseif (man.Action == "wn_railway") then
      s = processWNRailwayAction()
   elseif (man.Action == "wn_dest_parking") then
      s = processWNDestParkingAction(man)
   elseif (man.Action == "wn_dest_charging_station") then
      s = processWNDestChargingStationAction(man.project)
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
      s = processSRPFail(man.project)
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
        s = concat(mainString, " quindi ", s)      -- eng: and then
      else
        s = concat(mainString, " e poi ", s)      -- eng: and then
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