-- import laser lua library functions
require("lalulib")

local replaceWithFilenames = true
local replaceOnlySoundFiles = true

function getVersionInfo()
   local t =
   {
      ["country"] = "THA",          -- ISO 3166-1 alpha-3 country code
      ["lang"] = "tha",             -- ISO 639-3  alpha-3 language code
      ["version"] = "2.2.5",        -- version
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
      s = conc(spellNumber(distInM), "เมตร")
   end
   return s
end

function spellDistanceAndUnit2(distance)
   local s = ""
   local distInKm = getIntNumber((distance + 500)/1000)
   if (distInKm >= 1) then
      s = conc(spellNumber(distInKm), "กิโลเมตร")
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
         s = "ด้านซ้าย"
      elseif (position == 2) then
         s = "ด้านขวา"
      elseif (position == 3) then
         s = "ด้านกลาง"
      end
   else
      if (position == 1) then
         s = "ซ้าย"
      elseif (position == 2) then
         s = "ขวา"
      elseif (position == 3) then
         s = "กลาง"
      end
   end
   return s
end

function spellDirection(dir)
   local s = ""
   if (dir == 1) then
      s = "เลี้ยวซ้ายหักศอก"
   elseif (dir == 2) then
      s = "เลี้ยวซ้าย"
   elseif (dir == 3) then
      s = "เลี้ยวซ้ายเล็กน้อย"
   elseif (dir == 4) then
      s = "เลี้ยวขวาหักศอก"
   elseif (dir == 5) then
      s = "เลี้ยวขวา"
   elseif (dir == 6) then
      s = "เลี้ยวขวาเล็กน้อย"
   elseif (dir == 7) then
      s = "ตรงไป"
   elseif (dir == 8) then
      s = "กลับรถ"
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
   s = conc("จำกัดความเร็ว", limitedSpeed, "km/h. ความเร็วปัจจุบัน", currentSpeed, "km/h. ความเร็วของคุณเกินกำหนดแล้ว")
   return s
end

function getSafetyCamera(event)
   local s = ""
   if (event.red_light == 1) then
      s = conc("ข้างหน้ามีกล้องจับรถฝ่า")
   else
      s = conc("ข้างหน้ามีกล้องจับความเร็ว")
   end
   local currentDis = event.current_distance
   if (testDistance(currentDis, nil) and (spellDistanceAndUnit(currentDis) ~= "")) then
      s = conc("อีก", spellDistanceAndUnit(currentDis), s)
   else
      s = conc(s, "ahead")
   end
   local limitedSpeed = convToString(event.limited_speed)
   if (event.red_light == 1) then
      s = conc(s, ". สัญญาณไฟจราจร โปรดระวัง")
   elseif (limitedSpeed ~= "0") then
      s = conc(s, ".จำกัดความเร็ว", limitedSpeed, "km/h")
   end
   return s
end

function getTrafficMessageOrFlow(event)
   local s = ""
   local currentDis = event.current_distance
   if (testDistance2(currentDis, nil) and (spellDistanceAndUnit2(currentDis) ~= "")) then
      s = conc("ในประมาณ", spellDistanceAndUnit2(currentDis), ",")
   end
   if (event.traffic_content ~= "") then
      s = conc(s, "", getTrafficContent(event))
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
         s = conc(s, "แนะนำให้ใช้เลน")
      else
         s = conc(s, "โปรดใช้เลน")
      end
      local pos = ""
      if ((event.isInc2nd == 1) and (event.isInc3rd == 1)) then
         s = conc(s, "เลนที่สองและสาม")
         pos = spellPosition(event.isContiLaneVoice, event.position)
         s = conc(s, pos)
      elseif (event.isInc2nd == 1) then
         s = conc(s, "เลนที่สอง")
         pos = spellPosition(event.isContiLaneVoice, event.position)
         s = conc(s, pos)
      elseif (event.isInc3rd == 1) then
         s = conc(s, "เลนที่สาม")
         pos = spellPosition(event.isContiLaneVoice, event.position)
         s = conc(s, pos)
      elseif (((event.bestNumber == 1) or (event.number == 1)) and (event.position == 1)) then
         s = conc(s, "เลนซ้ายสุด")
      elseif (((event.bestNumber == 1) or (event.number == 1)) and (event.position == 2)) then
         s = conc(s, "เลนขวาสุด")
      else
         if (event.bestNumber > 1) then
            local bestNumber = convToString(event.bestNumber)
            s = conc(s, bestNumber, "เลน")
         elseif (event.number > 1) then
            local number = convToString(event.number)
            s = conc(s, number, "เลน")
         else
            s = conc(s, "เลน")
         end
         pos = spellPosition(0, event.position)
         s = conc(s, pos)
      end
   else
      if (event.isManeuver == 0) then
         local dir = spellDirection(event.dir)
         s = conc(dir, ".")
      end
      s = conc(s, "โปรดใช้เลน")
      if (event.number > 1) then
         local number = convToString(event.number)
         s = conc(s, number)
      end
      local pos = spellPosition(event.isContiLaneVoice, event.position)
      s = conc(s, "ที่", pos)
   end
   return s
end

function getTunnel(event)
   local s = "อุโมงค์ข้างหน้า"
   return s
end

function getSAPA(event)
   local s = ""
   if (testDistance3(event.current_distance, nil) and (spellDistanceAndUnit2(event.current_distance) ~= "")) then
      s = conc("อีก", spellDistanceAndUnit2(event.current_distance))
   end
   s = conc(s, spellCurrentType(event), "ข้างหน้า")
   if (event.next_type ~= "") then
      if (testDistance4(event.next_distance, nil) and (spellDistanceAndUnit2(event.next_distance) ~= "")) then
         s = conc(s, ". อีกอันอยู่ในระยะ", spellDistanceAndUnit2(event.next_distance))
      end
   end
   return s
end

function getBorder(event)
   local s = ""
   if (event.border_name ~= "") then
      s = conc("เข้าสู่", event.border_name)
   end
   return s
end

function getHighAccidentArea(event)
   local s = "ข้างหน้าเป็นบริเวณที่มีอุบัติเหตุเกิดขึ้นบ่อยครั้ง โปรดขับรถด้วยความระมัดระวัง"
   return s
end

function getRailway(event)
   local s = "ข้างหน้ามีทางรถไฟ โปรดขับรถด้วยความระมัดระวัง"
   return s
end

function getCurve(event)
   local s = ""
   if (event.continuous == 1) then
      s = "ข้างหน้ามีโค้งหักศอกอย่างต่อเนื่อง โปรดขับรถด้วยความระมัดระวัง"
   else
      s = "ข้างหน้ามีโค้งหักศอก โปรดขับรถด้วยความระมัดระวัง"
   end
   return s
end

function getSchoolZone(event)
   local s = "ข้างหน้าเป็นบริเวณโรงเรียน โปรดขับรถด้วยความระมัดระวัง"
   return s
end

function getLaneMerge(event)
   local s = ""
   if (event.type == 1) then
      s = "ข้างหน้ามีทางร่วมด้านซ้าย โปรดขับรถด้วยความระมัดระวัง"
   elseif (event.type == 2) then
      s = "ข้างหน้ามีทางร่วมด้านขวา โปรดขับรถด้วยความระมัดระวัง"
   end
   return s
end

function getDestination(event)
   local s = "ได้แนะนำที่จอดรถในจุดหมายปลายทางให้คุณแล้ว แตะเพื่อไป"
   return s
end

function getTrafficContent(event)
   local s = ""
   local trafficContent = convToString(event.traffic_content)
   if (trafficContent ~= "") then
      if (trafficContent == "congestion") then
         s = "มีช่วงที่รถติด"
      elseif (trafficContent == "accident") then
         s = "มีอุบัติเหตุเกิดขึ้น"
      elseif (trafficContent == "road work") then
         s = "มีงานก่อสร้างถนน"
      elseif (trafficContent == "narrow lanes") then
         s = "มีเลนแคบ ๆ"
      elseif (trafficContent == "obstacle") then
         s = "มีสิ่งกีดขวาง"
      elseif (trafficContent == "slippery road") then
         s = "ระวังถนนลื่น"
      elseif (trafficContent == "aquaplaning") then
         s = "ระวังรถไถลบนพื้นเปียก"
      elseif (trafficContent == "fire") then
         s = "มีไฟ"
      elseif (trafficContent == "road subsidence") then
         s = "ระวังถนนทรุดตัว"
      elseif (trafficContent == "disabled vehicle") then
         s = "มีรถเสีย"
      elseif (trafficContent == "road closed") then
         s = "มีการปิดถนน"
      elseif (trafficContent == "extreme weather conditions") then
         s = "มีสภาพอากาศที่รุนแรง"
      elseif (trafficContent == "reckless persons") then
         s = "มีคนซุ่มซ่าม"
      elseif (trafficContent == "over height warning system triggered") then
         s = "มีระบบเตือนความสูงเกินกำหนด"
      elseif (trafficContent == "traffic regulations changed") then
         s = "มีการเปลี่ยนแปลงกฎจราจร"
      elseif (trafficContent == "major event") then
         s = "มีกิจกรรมสำคัญ"
      elseif (trafficContent == "service not useable") then
         s = "มีบริการที่ไม่สามารถใช้งานได้"
      elseif (trafficContent == "slow moving vehicle") then
         s = "มีรถเคลื่อนที่ช้า"
      elseif (trafficContent == "dangerous end of queue") then
         s = "มีจุดสิ้นสุดที่อันตราย"
      elseif (trafficContent == "risk fo fire") then
         s = "มีความเสี่ยงเกิดไฟไหม้"
      elseif (trafficContent == "extra security measures") then
         s = "มีมาตรการรักษาความปลอดภัยพิเศษ"
      elseif (trafficContent == "police checkpoint") then
         s = "มีด่านตำรวจ"
      elseif (trafficContent == "malfunctioning roadside equipment") then
         s = "มีอุปกรณ์บนถนนชำรุด"
      end
   end
   return s
end

function spellCurrentType(event)-----translation is not given, translate by myself
   local s = ""
   if (event.current_type == "service area") then
     s = "พื้นที่ให้บริการ"
   elseif (event.current_type == "parking area") then
     s = "บริเวณที่จอดรถ"
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
