-- import laser lua library functions
require("lalulib")

local replaceWithFilenames = true
local replaceOnlySoundFiles = true

function getVersionInfo()
   local t =
   {
      ["country"] = "CHN",          -- ISO 3166-1 alpha-3 country code
      ["lang"] = "zho",             -- ISO 639-3  alpha-3 language code
      ["version"] = "1.0.1",        -- version
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
      s = conc(spellNumber(distInM), "米")
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
   if (distInKm >= 1) then
      s = conc(spellNumber(distInKm), "公里")
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

function spellPosition(position)
   local s = ""
   if (position == 1) then
      s = "左侧"
   elseif (position == 2) then
      s = "右侧"
   elseif (position == 3) then
      s = "中间"
   end
   return s
end

function spellDirection(dir)
   local s = ""
   if (dir == 1) then
      s = "左后"
   elseif (dir == 2) then
      s = "左转"
   elseif (dir == 3) then
      s = "左前"
   elseif (dir == 4) then
      s = "右后"
   elseif (dir == 5) then
      s = "右转"
   elseif (dir == 6) then
      s = "右前"
   elseif (dir == 7) then
      s = "直行"
   elseif (dir == 8) then
      s = "掉头"
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
      s = conc(s, "注意合流")
   elseif (event.Function == "merging_traffic_right") then
      s = conc(s, "注意右方合流")
   elseif (event.Function == "merging_traffic_left") then
      s = conc(s, "注意左方合流")
   elseif (event.Function == "railroad_crossing") then
      s = conc(s, "前方铁路道口")
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
   s = "速度限制" .. limitedSpeed .. "千米/小时. 您已超速"
   return s
end

function getSafetyCamera(event)
   local s = ""
   if (event.red_light == 1) then
      s = "有闯红灯摄像头"
   else
      s = "有速度摄像头"
   end
   local currentDis = event.current_distance
   if (testDistance(currentDis, nil) and (spellDistanceAndUnit(currentDis) ~= "")) then
      s = concat(spellDistanceAndUnit(currentDis) .. "内," .. s)
   else
      s = "前方" .. s
   end
   local limitedSpeed = convToString(event.limited_speed)
   if (event.red_light == 1) then
      s = s .. ".请小心驾驶"
   elseif (limitedSpeed ~= "0") then
      s = s .. ".当前限速" .. limitedSpeed .. "千米/小时"
   end
   return s
end

function getTrafficMessageOrFlow(event)
   local s = "前方"
   local currentDis = event.current_distance
   if (testDistance2(currentDis, nil) and (spellDistanceAndUnit2(currentDis) ~= "")) then
      s = concat(s .. "大约" .. spellDistanceAndUnit2(currentDis) .. "处，")
   end
   if (event.traffic_content ~= "") then
      s = concat(s .. "有" .. event.traffic_content)
   end
   return s
end

function getRecommendLane(event)
   local s = ""
   if (event.isContiLaneVoice == 1) then
      if (event.bestNumber > 0) then
         if (event.isManeuver == 0) then
            local dir = spellDirection(event.dir)
            s = dir .. ","
         end
         s = s .. "建议走"
      else
         s = s .. "请走"
      end
      local pos = spellPosition(event.position)
      if ((event.isInc2nd == 1) and (event.isInc3rd == 1)) then
         s = s .. pos .. "第二、三车线"
      elseif (event.isInc2nd == 1) then
         s = s .. pos .. "第二车线"
      elseif (event.isInc3rd == 1) then
         s = s .. pos .. "第三车线"
      elseif (((event.bestNumber == 1) or (event.number == 1)) and (event.position == 1)) then
         s = s .. "最左侧车线"
      elseif (((event.bestNumber == 1) or (event.number == 1)) and (event.position == 2)) then
         s = s .. "最右侧车线"
      else
         if (event.bestNumber > 1) then
            local bestNumber = convToString(event.bestNumber)
            s = s .. pos .. bestNumber .. "车线"
         elseif (event.number > 1) then
            local number = convToString(event.number)
            s = s .. pos .. number .. "车线"
         else
            s = s .. pos .. "车线"
         end
      end
   else
      if (event.isManeuver == 0) then
         local dir = spellDirection(event.dir)
         s = dir .. ","
      end
      s = s .. "请走"
      local pos = spellPosition(event.position)
      if (event.number > 1) then
         local number = convToString(event.number)
         s = s .. pos .. number .. "车线"
      else
         s = s .. pos .. "车线"
      end
   end
   return s
end

function getTunnel(event)
   local s = "前方隧道"
   return s
end

function getSAPA(event)
   local s = ""
   if (testDistance3(event.current_distance, nil) and (spellDistanceAndUnit2(event.current_distance) ~= "")) then
      s = concat(spellDistanceAndUnit2(event.current_distance), "", "内")
   end
   local SAPA_type = ""
   if (event.current_type == "parking area") then
      SAPA_type = "停车区"
   elseif (event.current_type == "service area") then
      SAPA_type = "服务区"
   end
   s = s .. "有" .. SAPA_type
   if (event.next_type ~= "") then
      if (testDistance4(event.next_distance, nil) and (spellDistanceAndUnit2(event.next_distance) ~= "")) then
         s = concat(s .. ",距离下一个" .. spellDistanceAndUnit2(event.next_distance))
      end
   end
   return s
end

function getBorder(event)
   local s = ""
   if (event.border_name ~= "") then
      s = concat("正在进入", "", event.border_name)
   end
   return s
end

function getHighAccidentArea(event)
   local s = "前方事故多发地带.请小心驾驶"
   return s
end

function getRailway(event)
   local s = "前方铁路.请小心驾驶"
   return s
end

function getCurve(event)
   local s = ""
   if (event.continuous == 1) then
      s = "前方连续急转弯.请小心驾驶"
   else
      s = "前方急转弯.请小心驾驶"
   end
   return s
end

function getSchoolZone(event)
   local s = "前方学校区域.请小心驾驶"
   return s
end

function getLaneMerge(event)
   local s = ""
   if (event.type == 1) then
      s = "前方左侧道路合流.请小心驾驶"
   elseif (event.type == 2) then
      s = "前方右侧道路合流.请小心驾驶"
   end
   return s
end

function getDestination(event)
   local s = "已为您推荐目的地停车场.点击前往"
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
