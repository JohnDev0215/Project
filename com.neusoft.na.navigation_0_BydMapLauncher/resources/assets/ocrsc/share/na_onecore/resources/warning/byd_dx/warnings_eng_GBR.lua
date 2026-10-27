-- import laser lua library functions
require("lalulib")

local replaceWithFilenames = true
local replaceOnlySoundFiles = true

function getVersionInfo()
   local t =
   {
      ["country"] = "GBR",          -- ISO 3166-1 alpha-3 country code
      ["lang"] = "eng",             -- ISO 639-3  alpha-3 language code
      ["version"] = "1.0.0",        -- version
      ["date"] = "2022-01-11",      -- date of the last change
      ["lua"] = _VERSION,           -- lua version
      ["exe"] = os.date( "%H:%M:%S %Y-%m-%d"),  -- execution date
      ["utc"] = os.date( "!%H:%M:%S %Y-%m-%d"), -- UTC execution date
      ["project"] = "byd_dx",    -- project title
      ["module"] = "warning",       -- guidance, traffic or warning
   }
   return dumpToString(t)
end

--      ["Function"] = "merging_traffic",   -- merging_traffic_right
                                            -- merging_traffic_left
                                            -- railroad_crossing
                                            -- speed_limited
                                            -- saftye_camera
                                            -- traffic_message_or_flow
                                            -- recommend_lane
                                            -- sapa
                                            -- border
                                            -- high_accident_area
                                            -- railway
                                            -- curve
                                            -- school_zone
                                            -- lane_merge
                                            -- destination
                                            -- version
--      ["current_speed"] = 70,             -- integer, current speed, unit is km/h, it is used in speed_limited, saftye_camera
--      ["limited_speed"] = 70,             -- integer, limit speed, unit is km/h, it is used in speed_limited, saftye_camera
--      ["current_distance"] = 500,         -- integer, the distance, unit is m, it is used in speed_limited, saftye_camera
--      ["red_light"] = 1,                  -- integer, 1 means has red light.


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

function spellDistanceAndUnit(distance)
   local s = ""
   local distInM = getIntNumber((distance + 50)/100) * 100
   if (distInM > 0) then
      s = conc(spellNumber(distInM), "meters")
   end
   return s
end

function spellDistanceAndUnit2(distance)
   local s = ""
   local distInKm = 0
   if (distance < 10000) then
      distInKm = getIntNumber((distance + 500)/1000)
   else
      distInKm = getIntNumber(distance/1000)
   end
   if (distInKm == 1) then
      s = conc(spellNumber(distInKm), "kilometre")
   elseif (distInKm > 1) then
      s = conc(spellNumber(distInKm), "kilometres")
   end
   return s
end

function spellNumber(numberValue)
   local s = ""
   if (isNumber(numberValue)) then
      local n = getIntNumber(numberValue)
      if (n > 0) then
         s = "" .. n
      end
   end
   return s
end

function spellPosition(isContiLaneVoice, position)
   local s = ""
   if (isContiLaneVoice == 1) then
      if (position == 1) then
         s = "on the left"
      elseif (position == 2) then
         s = "on the right"
      elseif (position == 3) then
         s = "on the center"
      end
   else
      if (position == 1) then
         s = "left"
      elseif (position == 2) then
         s = "right"
      elseif (position == 3) then
         s = "center"
      end
   end
   return s
end

function spellDirection(dir)
   local s = ""
   if (dir == 1) then
      s = "Turn sharp left"
   elseif (dir == 2) then
      s = "Turn left"
   elseif (dir == 3) then
      s = "Turn slight left"
   elseif (dir == 4) then
      s = "Turn sharp right"
   elseif (dir == 5) then
      s = "Turn right"
   elseif (dir == 6) then
      s = "Turn slight right"
   elseif (dir == 7) then
      s = "Go straight on"
   elseif (dir == 8) then
      s = "Make a u-turn"
   end
   return s
end

function getSound(event)
   local s = ""
   return s
end

function processMainFunction(event)
   local s = ""
   --main functions
   s = getSound(event)

   if (event.Function == "merging_traffic") then
      s = conc(s, "Be careful of merging traffic")
   elseif (event.Function == "merging_traffic_right") then
      s = conc(s, "Be careful of merging traffic from right")
   elseif (event.Function == "merging_traffic_left") then
      s = conc(s, "Be careful of merging traffic from left")
   elseif (event.Function == "railroad_crossing") then
      s = conc(s, "Railroad crossing ahead")
   elseif (event.Function == "speed_limited") then
      s = conc(s, getSpeedLimited(event))
   elseif (event.Function == "speed_camera") then
      s = conc(s, getSafetyCamera(event))
   elseif (event.Function == "traffic_message_or_flow") then
      s = conc(s, getTrafficMessageOrFlow(event))
   elseif (event.Function == "recommend_lane") then
      s = conc(s, getRecommendLane(event))
   elseif (event.Function == "tunnel") then
      s = conc(s, getTunnel(event))
   elseif (event.Function == "sapa") then
      s = conc(s, getSAPA(event))
   elseif (event.Function == "border") then
      s = conc(s, getBorder(event))
   elseif (event.Function == "high_accident_area") then
      s = conc(s, getHighAccidentArea(event))
   elseif (event.Function == "railway") then
      s = conc(s, getRailway(event))
   elseif (event.Function == "curve") then
      s = conc(s, getCurve(event))
   elseif (event.Function == "school_zone") then
      s = conc(s, getSchoolZone(event))
   elseif (event.Function == "lane_merge") then
      s = conc(s, getLaneMerge(event))
   elseif (event.Function == "destination") then
      s = conc(s, getDestination(event))
   elseif (event.Function == "version") then  --special version info
      s = getVersionInfo(event)
   end

   return s
end

function getSpeedLimited(event)
   local s = ""
   local currentSpeed = convToString(event.current_speed)
   local limitedSpeed = convToString(event.limited_speed)
   s = conc("Speed limit is", limitedSpeed, "km/h. Current speed is", currentSpeed, "km/h. You are exceeding the speed limit")
   return s
end

function getSafetyCamera(event)
   local s = ""
   if (event.red_light == 1) then
      s = conc("Red light violation camera")
   else
      s = conc("Speed camera")
   end
   local currentDis = event.current_distance
   if (testDistance(currentDis, nil) and (spellDistanceAndUnit(currentDis) ~= "")) then
      s = conc(s, "in", spellDistanceAndUnit(currentDis))
   else
      s = conc(s, "ahead")
   end
   local limitedSpeed = convToString(event.limited_speed)
   if (event.red_light == 1) then
      s = conc(s, ". Please be careful")
   elseif (limitedSpeed ~= "0") then
      s = conc(s, ". Speed limit is", limitedSpeed, "km/h")
   end
   return s
end

function getTrafficMessageOrFlow(event)
   local s = ""
   local currentDis = event.current_distance
   if (testDistance2(currentDis, nil) and (spellDistanceAndUnit2(currentDis) ~= "")) then
      s = conc("In about", spellDistanceAndUnit2(currentDis), ",")
   end
   if (event.traffic_content ~= "") then
      s = conc(s, "there is a", event.traffic_content)
   end
   return s
end

function getRecommendLane(event)
   local s = ""
   if (event.isContiLaneVoice == 1) then
      if (event.bestNumber > 0) then
         if (event.isManeuver == 0) then
            local dir = spellDirection(event.dir)
            s = conc(dir, ".")
         end
         s = conc(s, "Recommend taking the")
      else
         s = conc(s, "Please take the")
      end
      local pos = ""
      if ((event.isInc2nd == 1) and (event.isInc3rd == 1)) then
         s = conc(s, "second and third lanes")
         pos = spellPosition(event.isContiLaneVoice, event.position)
         s = conc(s, pos)
      elseif (event.isInc2nd == 1) then
         s = conc(s, "second lane")
         pos = spellPosition(event.isContiLaneVoice, event.position)
         s = conc(s, pos)
      elseif (event.isInc3rd == 1) then
         s = conc(s, "third lane")
         pos = spellPosition(event.isContiLaneVoice, event.position)
         s = conc(s, pos)
      elseif (((event.bestNumber == 1) or (event.number == 1)) and (event.position == 1)) then
         s = conc(s, "leftmost lane")
      elseif (((event.bestNumber == 1) or (event.number == 1)) and (event.position == 2)) then
         s = conc(s, "rightmost lane")
      else
         if (event.bestNumber > 1) then
            local bestNumber = convToString(event.bestNumber)
            s = conc(s, bestNumber, "lanes")
         elseif (event.number > 1) then
            local number = convToString(event.number)
            s = conc(s, number, "lanes")
         else
            s = conc(s, "lane")
         end
         pos = spellPosition(event.isContiLaneVoice, event.position)
         s = conc(s, pos)
      end
   else
      if (event.isManeuver == 0) then
         local dir = spellDirection(event.dir)
         s = conc(dir, ".")
      end
      s = conc(s, "Please take the")
      local pos = spellPosition(event.isContiLaneVoice, event.position)
      s = conc(s, pos)
      if (event.number > 1) then
         local number = convToString(event.number)
         s = conc(s, number, "lanes")
      else
         s = conc(s, "lane")
      end
   end
   return s
end

function getTunnel(event)
   local s = "Tunnel ahead"
   return s
end

function getSAPA(event)
   local s = ""
   if (testDistance3(event.current_distance, nil) and (spellDistanceAndUnit2(event.current_distance) ~= "")) then
      s = conc("In", spellDistanceAndUnit2(event.current_distance))
   end
   s = conc(s, event.current_type, "ahead")
   if (event.next_type ~= "") then
      if (testDistance4(event.next_distance, nil) and (spellDistanceAndUnit2(event.next_distance) ~= "")) then
         s = conc(s, ". The next one is in", spellDistanceAndUnit2(event.next_distance))
      end
   end
   return s
end

function getBorder(event)
   local s = ""
   if (event.border_name ~= "") then
      s = conc("Entering", event.border_name)
   end
   return s
end

function getHighAccidentArea(event)
   local s = "High accident area ahead. Please drive carefully"
   return s
end

function getRailway(event)
   local s = "Railway ahead. Please drive carefully"
   return s
end

function getCurve(event)
   local s = ""
   if (event.continuous == 1) then
      s = "Continuous curve ahead. Please drive carefully"
   else
      s = "Curve ahead. Please drive carefully"
   end
   return s
end

function getSchoolZone(event)
   local s = "School zone ahead. Please drive carefully"
   return s
end

function getLaneMerge(event)
   local s = ""
   if (event.type == 1) then
      s = "Left side"
   elseif (event.type == 2) then
      s = "Right side"
   end
   s = conc(s, "merger ahead. Please drive carefully")
   return s
end

function getDestination(event)
   local s = "The destination parking lot has been recommended for you, click Go"
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
      ["Fanfon"] = {"PINPON_4"},
      ["Pi"] = {"otonin_k"},
      ["Pinpon"] = {"PINPON_5"},
      ["Ponpon"] = {"PONPO-N"},
   }
   return tokens
end

function replaceTokens(promptTable, wavPath)
   local s = ""
   local dirSeparator = package.config:sub(1,1)
   local tokens = {}

   if (replaceOnlySoundFiles) then
      tokens = getSoundFileTokens()
   else
      tokens = getWaveFileTokens()
      for key, val in pairs(getSoundFileTokens()) do tokens[key] = val end  -- merge tables
   end
   wavPath = convToString(wavPath)

   if (isNotEmpty(wavPath)) then
      if (string.sub(wavPath, -1) ~= dirSeparator) then
         wavPath = wavPath .. dirSeparator
      end
      local genericWavPath = wavPath .. "generic" .. dirSeparator

      local markTbl = {}
      if (not replaceOnlySoundFiles) then
         markTbl =   {
            ["."] = integrateAudioSeq("A17_0003.wav", genericWavPath), -- silence 0.3 seconds
            [","] = integrateAudioSeq("A17_0001.wav", genericWavPath), -- silence 0.1 seconds
         }
      else
         markTbl =   {
            ["."] = ".",
            [","] = ",",
         }
      end

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
               if (replaceOnlySoundFiles) then
                  appendix = ""  -- no appendix, because in some cases point is pronounced as dot
               end
            end
         end
         if (replaceOnlySoundFiles) then
            s = conc(s, promptPart .. appendix)
         else
            s = s .. promptPart .. appendix
         end
      end
   end
   return s
end

function main(event)
   local s = ""
   local isVersionRequest = false

   logPrint(dump(event))

   if ((type(event) == "table") and (event ~= nil)) then
      if (convToString(event.Function) == "") then
         event.Function = event.Cause
      end
      s = processMainFunction(event)
      isVersionRequest = (event.Function == "version")
   end

   if ((#s > 0) and (not isVersionRequest)) then
      s = s .. "."
   end
   if ((replaceWithFilenames) and (not isVersionRequest) and (isNotEmpty(event.VoiceDataPath))) then
      local v = s
      local t = split(s)
      s = replaceTokens(t, event.VoiceDataPath)
      if (s ~= v) then
         logPrint("WarningIntermediateResult: " .. v)
      end
   end

   logPrint("WarningResult: " .. s)
   return s
end

function dump(...)
   local s = ""
   for j = 1, select('#', ...) do
      local object = select(j, ...)
      s =  conc(s, "WarningParameter ", j, ": ", dumpToString(object))
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
startlua.spellDistanceAndUnit = spellDistanceAndUnit
startlua.getWaveFileTokens = getSoundFileTokens
startlua.getSoundFileTokens = getSoundFileTokens
startlua.changeWaveFileReplacement = changeWaveFileReplacement

return startlua
